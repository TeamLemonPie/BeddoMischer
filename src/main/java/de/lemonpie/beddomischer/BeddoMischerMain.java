package de.lemonpie.beddomischer;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import de.lemonpie.beddomischer.http.handler.*;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.http.websocket.listener.BoardCallbackListener;
import de.lemonpie.beddomischer.http.websocket.listener.PlayerListWebListener;
import de.lemonpie.beddomischer.http.websocket.listener.WebSocketCountdownListener;
import de.lemonpie.beddomischer.http.websocket.listener.WinProbabilityPlayerListener;
import de.lemonpie.beddomischer.listener.CountdownListener;
import de.lemonpie.beddomischer.model.BlockOption;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerList;
import de.lemonpie.beddomischer.socket.CommandExecutor;
import de.lemonpie.beddomischer.socket.ControlServerSocket;
import de.lemonpie.beddomischer.socket.admin.AdminBoardListener;
import de.lemonpie.beddomischer.socket.admin.AdminPlayerListListener;
import de.lemonpie.beddomischer.socket.admin.AdminServerSocket;
import de.lemonpie.beddomischer.socket.admin.command.read.*;
import de.lemonpie.beddomischer.socket.admin.command.read.player.*;
import de.lemonpie.beddomischer.socket.director.DirectorServerSocket;
import de.lemonpie.beddomischer.socket.reader.CardReadCommand;
import de.lemonpie.beddomischer.socket.reader.ReaderServerSocket;
import de.lemonpie.beddomischer.storage.BoardSerializer;
import de.lemonpie.beddomischer.storage.StorageBoardListener;
import de.lemonpie.beddomischer.storage.StoragePlayerListListener;
import de.tobias.logger.FileOutputOption;
import de.tobias.logger.LogLevelFilter;
import de.tobias.logger.Logger;
import de.tobias.logger.Slf4JLoggerAdapter;
import de.tobias.utils.net.DiscoveryThread;
import de.tobias.utils.settings.Storage;
import de.tobias.utils.settings.StorageTypes;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import static spark.Spark.*;

public class BeddoMischerMain
{

	public static final int READER_NULL_ID = -3;

	private static PlayerList players;
	private static Board board;

	private static BlockOption blockOption;

	private static long pauseEndTime;
	private static long pauseStartTime;
	private static List<CountdownListener> countdownListeners;

	private static WebSocketHandler webSocketHandler;

	private static ControlServerSocket rfidServerSocket;
	private static ControlServerSocket controlServerSocket;
	private static ControlServerSocket directorServerSocket;


	static
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
		}
		catch(ClassNotFoundException e)
		{
			Logger.error(e);
		}
	}

	private static Dao<Player, Integer> playerDao;

	private static void prepareLogger()
	{
		try
		{
			Path logFolder = Paths.get(BeddoMischerMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
			Logger.init(logFolder);
			Logger.setLevelFilter(LogLevelFilter.DEBUG);
			Slf4JLoggerAdapter.disableSlf4jDebugPrints();
			Logger.setFileOutput(FileOutputOption.COMBINED);
		}
		catch(URISyntaxException e1)
		{
			Logger.error(e1);
		}

		Logger.info("Launching App: {0}, version: {1}, build: {2}, date: {3}", "BeddoMischer", "1.1.0", "3", "07.08.18");
	}

	public static void main(String[] args) throws SQLException
	{
		prepareLogger();

		Path settingsPath = Paths.get("./settings.yml");
		ServerSettings serverSettings = Storage.load(settingsPath, StorageTypes.YAML, ServerSettings.class);

		// Setup jdbc
		final String databaseUrl = "jdbc:sqlite:BeddoMischer.db";
		final JdbcConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

		playerDao = DaoManager.createDao(connectionSource, Player.class);
		TableUtils.createTableIfNotExists(connectionSource, Player.class);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try
			{
				connectionSource.close();
			}
			catch(IOException e)
			{
				Logger.error(e);
			}
		}));

		// Starting discovery thread
		final DiscoveryThread discovery = DiscoveryThread.getInstance();
		discovery.setMessageKey("BEDDOMISCHER");
		discovery.setPort(9990);

		Thread discoveryThread = new Thread(discovery);
		discoveryThread.start();

		Logger.debug("Listening for discovery requests at port: " + String.valueOf(discovery.getPort()) + " with key: " + discovery.getMessageKey());

		startUp();
		loadData();
		startServer(serverSettings);
		startWebServer(serverSettings);
	}

	public static void startUp()
	{
		players = new PlayerList();
		board = new Board();
		blockOption = BlockOption.NONE;

		countdownListeners = new LinkedList<>();

		registerCommands();
	}

	public static void loadData() throws SQLException
	{
		board = BoardSerializer.loadBoard();
		board.addListener(new StorageBoardListener());

		// Load data from database
		players.addListener(new StoragePlayerListListener());
		players.addAll(playerDao.queryForAll());
		players.updateListener();
	}

	public static void startServer(ServerSettings settings)
	{
		try
		{
			rfidServerSocket = new ReaderServerSocket(settings.readerInterface, settings.readerPort);
		}
		catch(IOException e)
		{
			Logger.error(e);
		}

		try
		{
			controlServerSocket = new AdminServerSocket(settings.controlInterface, settings.controlPort);
		}
		catch(IOException e)
		{
			Logger.error(e);
		}

		try
		{
			controlServerSocket = new DirectorServerSocket(settings.directorInterface, settings.directorPort);
		}
		catch(IOException e)
		{
			Logger.error(e);
		}

		// Add Listener
		AdminPlayerListListener adminPlayerListListener = new AdminPlayerListListener();
		players.addListener(adminPlayerListListener);
		board.addListener(new AdminBoardListener());
		for(Player currentPlayer : players)
		{
			adminPlayerListListener.addPlayer(currentPlayer);
		}
	}

	public static void startWebServer(ServerSettings settings)
	{
		ipAddress(settings.httpInterface);
		port(settings.httpPort);

		exception(Exception.class, (exception, req, res) -> {
			Logger.error(exception);
			Spark.halt(500, "internal error: " + exception.getLocalizedMessage());
		});

		Configuration freeMarkerConfiguration = new Configuration(Configuration.VERSION_2_3_0);
		freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(BeddoMischerMain.class, "/template/"));

		Spark.staticFileLocation("/public");
		webSocket("/callback", webSocketHandler = new WebSocketHandler());

		// Add listener
		PlayerListWebListener playerListWebListener = new PlayerListWebListener(webSocketHandler);
		players.addListener(playerListWebListener);
		addCountdownListener(new WebSocketCountdownListener(webSocketHandler));
		board.addListener(new BoardCallbackListener(webSocketHandler));
		board.addListener(new WinProbabilityPlayerListener(webSocketHandler));
		for(Player currentPlayer : players)
		{
			playerListWebListener.addPlayer(currentPlayer);
		}

		// Add routes
		get("/admin", new AdminHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/countdown", new CountdownHandler(false), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/countdown_transparent", new CountdownHandler(true), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/chips", new ChipListHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/chips/:id", new ChipGetHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/player", new PlayerListHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/player/:id", new PlayerGetHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/player_feedback", new PlayerFeedbackGetHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/board", new BoardHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
	}

	public static void closeServer() throws IOException
	{
		controlServerSocket.close();
		rfidServerSocket.close();
		directorServerSocket.close();
	}

	private static void registerCommands()
	{
		final CommandExecutor commandExecutor = CommandExecutor.getInstance();

		// Reader Command
		commandExecutor.addCommand(new CardReadCommand(), Scope.READER);

		// Admin Command
		commandExecutor.addCommand(new ClearReadCommand(), Scope.ADMIN);
		commandExecutor.addCommand(new ReaderReadCommand(), Scope.ADMIN);
		commandExecutor.addCommand(new BoardCardSetReadCommand(), Scope.ADMIN);

		commandExecutor.addCommand(new PlayerOpReadCommand(), Scope.ADMIN);

		commandExecutor.addCommand(new PlayerNameReadCommand(), Scope.ADMIN);
		commandExecutor.addCommand(new PlayerTwitchNameReadCommand(), Scope.ADMIN);
		commandExecutor.addCommand(new PlayerChipsReadCommand(), Scope.ADMIN);
		commandExecutor.addCommand(new PlayerStateReadCommand(), Scope.ADMIN);

		commandExecutor.addCommand(new DataReadCommand(), Scope.ADMIN);

		commandExecutor.addCommand(new BlockReadCommand(), Scope.ADMIN);

		commandExecutor.addCommand(new CountdownSetReadCommand(), Scope.ADMIN);

		commandExecutor.addCommand(new BigBlindReadCommand(), Scope.ADMIN);
		commandExecutor.addCommand(new SmallBlindReadCommand(), Scope.ADMIN);
		commandExecutor.addCommand(new AnteReadCommand(), Scope.ADMIN);
	}

	/*
	 * Data accessors
	 */

	public static Dao<Player, Integer> getPlayerDao()
	{
		return playerDao;
	}

	public static PlayerList getPlayers()
	{
		return players;
	}

	public static Board getBoard()
	{
		return board;
	}

	public static BlockOption getBlockOption()
	{
		return blockOption;
	}

	public static void setBlockOption(BlockOption blockOption)
	{
		BeddoMischerMain.blockOption = blockOption;
	}

	public static ControlServerSocket getRfidServerSocket()
	{
		return rfidServerSocket;
	}

	public static ControlServerSocket getControlServerSocket()
	{
		return controlServerSocket;
	}

	/*
	Countdown
	 */
	public static long getPauseEndTime()
	{
		return pauseEndTime;
	}

	public static void setPauseEndTime(long pauseEndTime)
	{
		BeddoMischerMain.pauseEndTime = pauseEndTime;
		fireListener(l -> l.pauseCountdownDidChange(pauseEndTime));
	}

	public static long getPauseStartTime()
	{
		return pauseStartTime;
	}

	public static void setPauseStartTime(long pauseStartTime)
	{
		BeddoMischerMain.pauseStartTime = pauseStartTime;
		fireListener(l -> l.gameCountdownDidChange(pauseStartTime));
	}

	public static void addCountdownListener(CountdownListener countdownListener)
	{
		countdownListeners.add(countdownListener);
	}

	private static void fireListener(Consumer<CountdownListener> consumer)
	{
		for(CountdownListener countdownListener : countdownListeners)
		{
			consumer.accept(countdownListener);
		}
	}
}
