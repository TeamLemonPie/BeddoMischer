package de.lemonpie.beddomischer;

import de.lemonpie.beddomischer.http.handler.BoardHandler;
import de.lemonpie.beddomischer.http.handler.PlayerHandler;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.http.websocket.listener.BoardCallbackListener;
import de.lemonpie.beddomischer.http.websocket.listener.PlayerCallbackListener;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.settings.Settings;
import de.lemonpie.beddomischer.settings.SettingsHandler;
import de.lemonpie.beddomischer.socket.ControlServerSocket;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class BeddoMischerMain {

	private static List<Player> players;
	private static Board board;

	private static WebSocketHandler webSocketHandler;

	private static ControlServerSocket rfidServerSocket;
	private static ControlServerSocket controlServerSocket;

	public static void main(String[] args) {
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

		players = new ArrayList<>();
		board = new Board();

		try {
			rfidServerSocket = new ControlServerSocket(settings.readerInterface(), settings.readerPort());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			controlServerSocket = new ControlServerSocket(settings.controlInterface(), settings.controlPort());
		} catch (IOException e) {
			e.printStackTrace();
		}

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

		get("/player", new PlayerHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
		get("/board", new BoardHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
	}

	public static List<Player> getPlayers() {
		return players;
	}

	public static Player addPlayer() {
		Player player = new Player(players.size());
		PlayerCallbackListener playerCallbackListener = new PlayerCallbackListener(player, webSocketHandler);
		player.addListener(playerCallbackListener);
		players.add(player);
		return player;
	}

	public static Board getBoard() {
		return board;
	}

	private static void initBoard() {
		BoardCallbackListener boardCallbackListener = new BoardCallbackListener(webSocketHandler);
		board.addListener(boardCallbackListener);
	}
}
