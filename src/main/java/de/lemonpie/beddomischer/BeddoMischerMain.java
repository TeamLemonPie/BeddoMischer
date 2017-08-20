package de.lemonpie.beddomischer;

import de.lemonpie.beddomischer.http.handler.BoardHandler;
import de.lemonpie.beddomischer.http.handler.PlayerHandler;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.http.websocket.listener.PlayerCallbackListener;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class BeddoMischerMain {

	private static List<Player> players;
	private static Board board;

	private static WebSocketHandler webSocketHandler;

	public static void main(String[] args) {
		players = new ArrayList<>();
		board = new Board();

		port(9999);

		exception(Exception.class, (exception, req, res) -> {
			exception.printStackTrace();
			Spark.halt(500, "internal error: " + exception.getLocalizedMessage());
		});

		Configuration freeMarkerConfiguration = new Configuration(Configuration.VERSION_2_3_0);
		freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(BeddoMischerMain.class, "/template/"));

		Spark.staticFileLocation("/public");
		webSocket("/callback", webSocketHandler = new WebSocketHandler());

		addPlayer();

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
}
