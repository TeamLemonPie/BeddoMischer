package de.lemonpie.beddomischer;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import de.lemonpie.beddomischer.http.handler.*;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.http.websocket.listener.BoardCallbackListener;
import de.lemonpie.beddomischer.http.websocket.listener.PlayerListWebListener;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerList;
import de.lemonpie.beddomischer.model.reader.CardReader;
import de.lemonpie.beddomischer.settings.Settings;
import de.lemonpie.beddomischer.settings.SettingsHandler;
import de.lemonpie.beddomischer.socket.ControlServerSocket;
import de.lemonpie.beddomischer.socket.admin.AdminPlayerListListener;
import de.lemonpie.beddomischer.socket.admin.AdminServerSocket;
import de.lemonpie.beddomischer.socket.reader.ReaderServerSocket;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static spark.Spark.*;

public class BeddoMischerMain {

    private static PlayerList players;
    private static Board board;
    private static List<CardReader> cardReaders;

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

    private static Dao<Player, Integer> playerIntegerDao;

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
        cardReaders = new ArrayList<>();

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

        String databaseUrl = "jdbc:sqlite:BeddoMischer.db";
        JdbcConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

        playerIntegerDao = DaoManager.createDao(connectionSource, Player.class);
        TableUtils.createTableIfNotExists(connectionSource, Player.class);

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

        initBoard();

        // Add Listener
        players.addListener(new PlayerListWebListener(webSocketHandler));
        players.addListener(new AdminPlayerListListener());
        players.addListener(new StoragePlayerListListener());

        // Load data from database
        players.addAll(playerIntegerDao.queryForAll());

        get("/chips", new ChipListHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
        get("/chips/:id", new ChipGetHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
        get("/player", new PlayerListHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
        get("/player/:id", new PlayerGetHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
        get("/board", new BoardHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
    }

    public static Dao<Player, Integer> getPlayerDao() {
        return playerIntegerDao;
    }

    public static PlayerList getPlayers() {
        return players;
    }

    public static Board getBoard() {
        return board;
    }

    private static void initBoard() {
        BoardCallbackListener boardCallbackListener = new BoardCallbackListener(webSocketHandler);
        board.addListener(boardCallbackListener);
    }

    public static ControlServerSocket getRfidServerSocket() {
        return rfidServerSocket;
    }

    public static ControlServerSocket getControlServerSocket() {
        return controlServerSocket;
    }

    public static List<CardReader> getCardReaders() {
        return cardReaders;
    }

    public static Optional<CardReader> getCardReader(int readerId) {
        return cardReaders.stream().filter(r -> r.getReaderId() == readerId).findFirst();
    }
}
