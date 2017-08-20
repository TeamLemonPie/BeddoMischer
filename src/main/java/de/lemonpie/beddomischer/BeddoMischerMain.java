package de.lemonpie.beddomischer;

import de.lemonpie.beddomischer.http.handler.PlayerHandler;
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

	public static void main(String[] args) {
		players = new ArrayList<>();

		port(9999);

		exception(Exception.class, (exception, req, res) -> {
			exception.printStackTrace();
			Spark.halt(500, "internal error: " + exception.getLocalizedMessage());
		});

		Configuration freeMarkerConfiguration = new Configuration(Configuration.VERSION_2_3_0);
		freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(BeddoMischerMain.class, "/template/"));

		Spark.staticFileLocation("/public");
		get("/player", new PlayerHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
	}

	public static List<Player> getPlayers() {
		return players;
	}
}
