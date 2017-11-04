package de.lemonpie.beddomischer;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import de.lemonpie.beddomischer.http.handler.*;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.http.websocket.listener.BoardCallbackListener;
import de.lemonpie.beddomischer.http.websocket.listener.PlayerListWebListener;
import de.lemonpie.beddomischer.http.websocket.listener.WinProbabilityPlayerListener;
import de.lemonpie.beddomischer.model.*;
import de.lemonpie.beddomischer.model.reader.BoardCardReader;
import de.lemonpie.beddomischer.model.reader.PlayerCardReader;
import de.lemonpie.beddomischer.settings.Settings;
import de.lemonpie.beddomischer.settings.SettingsHandler;
import de.lemonpie.beddomischer.socket.ControlServerSocket;
import de.lemonpie.beddomischer.socket.admin.AdminBoardListener;
import de.lemonpie.beddomischer.socket.admin.AdminPlayerListListener;
import de.lemonpie.beddomischer.socket.admin.AdminServerSocket;
import de.lemonpie.beddomischer.socket.reader.ReaderServerSocket;
import de.lemonpie.beddomischer.storage.StorageCardReaderListListener;
import de.lemonpie.beddomischer.storage.StoragePlayerListListener;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import static spark.Spark.*;

public class BeddoMischerMain {

	private static PlayerList players;
	private static Board board;

	private static BlockOption blockOption;

	private static CardReaderList cardReaders;
	private static long countdownEndTime;

	private static WebSocketHandler webSocketHandler;

	private static ControlServerSocket rfidServerSocket;
	private static ControlServerSocket controlServerSocket;


	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Dao<Player, Integer> playerDao;
	private static Dao<BoardCardReader, Integer> boardCardReaderDao;
	private static Dao<PlayerCardReader, Integer> playerCardReaderDao;

	public static void main(String[] args) throws SQLException {
		Path settingsPath = Paths.get("settings.properties");
		Settings settings = new Settings();
		try {
			if (Files.notExists(settingsPath)) {
				SettingsHandler.saver().defaultSettings(settingsPath);
			}
			settings = SettingsHandler.loader().load(settingsPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		players = new PlayerList();
		board = new Board();
		blockOption = BlockOption.NONE;
		cardReaders = new CardReaderList();

		try {
			rfidServerSocket = new ReaderServerSocket(settings.readerInterface(), settings.readerPort());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			controlServerSocket = new AdminServerSocket(settings.controlInterface(), settings.controlPort());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Setup jdbc
		final String databaseUrl = "jdbc:sqlite:BeddoMischer.db";
		final JdbcConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

		playerDao = DaoManager.createDao(connectionSource, Player.class);
		boardCardReaderDao = DaoManager.createDao(connectionSource, BoardCardReader.class);
		playerCardReaderDao = DaoManager.createDao(connectionSource, PlayerCardReader.class);
		TableUtils.createTableIfNotExists(connectionSource, Player.class);
		TableUtils.createTableIfNotExists(connectionSource, BoardCardReader.class);
		TableUtils.createTableIfNotExists(connectionSource, PlayerCardReader.class);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				connectionSource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}));

		ipAddress(settings.httpInterface());
		port(settings.httpPort());

		exception(Exception.class, (exception, req, res) -> {
			exception.printStackTrace();
			Spark.halt(500, "internal error: " + exception.getLocalizedMessage());
		});

		Configuration freeMarkerConfiguration = new Configuration(Configuration.VERSION_2_3_0);
		freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(BeddoMischerMain.class, "/template/"));

		Spark.staticFileLocation("/public");
		webSocket("/callback", webSocketHandler = new WebSocketHandler());

		initBoardListener();

		// Add Listener
		players.addListener(new PlayerListWebListener(webSocketHandler));
		players.addListener(new AdminPlayerListListener());
		players.addListener(new StoragePlayerListListener());

		board.addListener(new AdminBoardListener());

		cardReaders.addListener(new StorageCardReaderListListener());

		// Load data from database
		players.addAll(playerDao.queryForAll());

		cardReaders.addAll(boardCardReaderDao.queryForAll());
		cardReaders.addAll(playerCardReaderDao.queryForAll());

		get("/countdown", new CountdownHandler(false), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/countdown_transparent", new CountdownHandler(true), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/chips", new ChipListHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/chips/:id", new ChipGetHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/player", new PlayerListHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/player/:id", new PlayerGetHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/board", new BoardHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
	}

	public static Dao<Player, Integer> getPlayerDao() {
		return playerDao;
	}

	public static Dao<BoardCardReader, Integer> getBoardCardReaderDao() {
		return boardCardReaderDao;
	}

	public static Dao<PlayerCardReader, Integer> getPlayerCardReaderDao() {
		return playerCardReaderDao;
	}

	public static PlayerList getPlayers() {
		return players;
	}

	public static Board getBoard() {
		return board;
	}

	public static BlockOption getBlockOption() {
		return blockOption;
	}

	public static void setBlockOption(BlockOption blockOption) {
		BeddoMischerMain.blockOption = blockOption;
	}

	private static void initBoardListener() {
		board.addListener(new BoardCallbackListener(webSocketHandler));
		board.addListener(new WinProbabilityPlayerListener(webSocketHandler));
	}

	public static ControlServerSocket getRfidServerSocket() {
		return rfidServerSocket;
	}

	public static ControlServerSocket getControlServerSocket() {
		return controlServerSocket;
	}

	public static CardReaderList getCardReaders() {
		return cardReaders;
	}

	public static long getCountdownEndTime() {
		return countdownEndTime;
	}

	public static void setCountdownEndTime(long countdownEndTime) {
		BeddoMischerMain.countdownEndTime = countdownEndTime;
	}
}
