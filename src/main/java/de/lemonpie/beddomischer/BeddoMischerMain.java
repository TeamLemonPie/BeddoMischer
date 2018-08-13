package de.lemonpie.beddomischer;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import de.lemonpie.beddocommon.model.lowerthird.LowerThird;
import de.lemonpie.beddocommon.model.lowerthird.LowerThirdList;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.lemonpie.beddocommon.network.server.ControlServerSocket;
import de.lemonpie.beddomischer.http.handler.*;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.http.websocket.listener.BoardCallbackListener;
import de.lemonpie.beddomischer.http.websocket.listener.PlayerListWebListener;
import de.lemonpie.beddomischer.http.websocket.listener.WebSocketCountdownListener;
import de.lemonpie.beddomischer.http.websocket.listener.WinProbabilityListener;
import de.lemonpie.beddomischer.model.BlockOption;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.CountdownHandler;
import de.lemonpie.beddomischer.model.player.Player;
import de.lemonpie.beddomischer.model.player.PlayerList;
import de.lemonpie.beddomischer.socket.admin.AdminBoardListener;
import de.lemonpie.beddomischer.socket.admin.AdminServerSocket;
import de.lemonpie.beddomischer.socket.admin.BeddoControlPlayerListListener;
import de.lemonpie.beddomischer.socket.admin.command.read.*;
import de.lemonpie.beddomischer.socket.admin.command.read.player.*;
import de.lemonpie.beddomischer.socket.director.DirectorServerSocket;
import de.lemonpie.beddomischer.socket.reader.CardReadCommand;
import de.lemonpie.beddomischer.socket.reader.ReaderServerSocket;
import de.lemonpie.beddomischer.storage.BoardSerializer;
import de.lemonpie.beddomischer.storage.StorageBoardListener;
import de.lemonpie.beddomischer.storage.StorageLowerThirdListListener;
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

import static spark.Spark.*;

public class BeddoMischerMain
{

	public static final int READER_NULL_ID = -3;

	private static PlayerList players;
	private static LowerThirdList lowerThirds;
	private static Board board;

	private static BlockOption blockOption;

	private static ControlServerSocket rfidServerSocket = null;
	private static ControlServerSocket controlServerSocket = null;
	private static ControlServerSocket directorServerSocket = null;


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
	private static Dao<LowerThird, Integer> lowerThirdDao;

	private static void prepareLogger()
	{
		try
		{
			Path logFolder = Paths.get(BeddoMischerMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
			Logger.init(logFolder);

			Slf4JLoggerAdapter.disableSlf4jDebugPrints();
			Logger.setLevelFilter(LogLevelFilter.DEBUG);
			Logger.setFileOutput(FileOutputOption.COMBINED);
		}
		catch(URISyntaxException e1)
		{
			Logger.error(e1);
		}

		Logger.info("Launching App: {0}, version: {1}, build: {2}, date: {3}", "BeddoMischer", "1.1.0", "3", "07.08.18");
	}

	public static void main(String[] args)
	{
		try
		{
			prepareLogger();

			Path settingsPath = Paths.get("./settings.yml");
			ServerSettings serverSettings = Storage.load(settingsPath, StorageTypes.YAML, ServerSettings.class);

			// Setup jdbc
			final JdbcConnectionSource connectionSource = setupDataSource();

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				try
				{
					closeServer();
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
			startSocketServer(serverSettings);
			startWebServer(serverSettings);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private static JdbcConnectionSource setupDataSource() throws SQLException
	{
		final String databaseUrl = "jdbc:sqlite:BeddoMischer.db";
		final JdbcConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

		playerDao = DaoManager.createDao(connectionSource, Player.class);
		lowerThirdDao = DaoManager.createDao(connectionSource, LowerThird.class);

		TableUtils.createTableIfNotExists(connectionSource, Player.class);
		TableUtils.createTableIfNotExists(connectionSource, LowerThird.class);

		return connectionSource;
	}

	static void startUp()
	{
		players = new PlayerList();
		lowerThirds = new LowerThirdList();
		board = new Board();
		blockOption = BlockOption.NONE;

		registerCommands();
	}

	private static void loadData() throws SQLException
	{
		board = BoardSerializer.loadBoard();
		board.addListener(new StorageBoardListener());


		lowerThirds.addListener(new StorageLowerThirdListListener());
		lowerThirds.addAll(lowerThirdDao.queryForAll());

		players.addListener(new StoragePlayerListListener());

		players.addAll(playerDao.queryForAll());
		players.updateListener();
	}

	private static void startSocketServer(ServerSettings settings)
	{
		rfidServerSocket = new ReaderServerSocket(settings.readerInterface, settings.readerPort);
		controlServerSocket = new AdminServerSocket(settings.controlInterface, settings.controlPort);
		directorServerSocket = new DirectorServerSocket(settings.directorInterface, settings.directorPort);

		// Add Listener
		board.addListener(new AdminBoardListener());

		BeddoControlPlayerListListener beddoControlPlayerListListener = new BeddoControlPlayerListListener();
		players.addListener(beddoControlPlayerListListener);
		players.forEach(beddoControlPlayerListListener::addObjectToList); // Initial Run for existing player
	}

	public static void startWebServer(ServerSettings settings)
	{
		ipAddress(settings.httpInterface);
		port(settings.httpPort);

		exception(Exception.class, (exception, req, res) -> {
			Logger.error(exception);
			Spark.halt(500, "internal error: " + exception.getLocalizedMessage());
		});

		final Configuration freeMarkerConfiguration = new Configuration(Configuration.VERSION_2_3_0);
		freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(BeddoMischerMain.class, "/template/"));
		final FreeMarkerEngine engine = new FreeMarkerEngine(freeMarkerConfiguration);

		Spark.staticFileLocation("/public");
		WebSocketHandler webSocketHandler;
		webSocket("/callback", webSocketHandler = new WebSocketHandler());

		// Add listener
		CountdownHandler.getInstance().addCountdownListener(new WebSocketCountdownListener(webSocketHandler));

		board.addListener(new BoardCallbackListener(webSocketHandler));
		board.addListener(new WinProbabilityListener(webSocketHandler));

		PlayerListWebListener playerListWebListener = new PlayerListWebListener(webSocketHandler);
		players.addListener(playerListWebListener);
		players.forEach(playerListWebListener::addObjectToList);

		// Add routes
		get("/admin", new AdminHandler(), engine);
		get("/countdown", new CountdownGetHandler(false), engine);
		get("/countdown_transparent", new CountdownGetHandler(true), engine);
		get("/chips", new ChipListHandler(), engine);
		get("/chips/:id", new ChipGetHandler(), engine);
		get("/player", new PlayerListHandler(), engine);
		get("/player/:id", new PlayerGetHandler(), engine);
		get("/player_feedback", new PlayerFeedbackGetHandler(), engine);
		get("/board", new BoardHandler(), engine);
		get("/lowerthird/:id", new LowerThirdGetHandler());
	}

	public static void closeServer() throws IOException
	{
		controlServerSocket.close();
		rfidServerSocket.close();
		directorServerSocket.close();
		Spark.stop();
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
		commandExecutor.addCommand(new PlayerHighlightReadCommand(), Scope.ADMIN);

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

	public static Dao<LowerThird, Integer> getLowerThirdDao()
	{
		return lowerThirdDao;
	}

	public static PlayerList getPlayers()
	{
		return players;
	}

	public static LowerThirdList getLowerThirds()
	{
		return lowerThirds;
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
}
