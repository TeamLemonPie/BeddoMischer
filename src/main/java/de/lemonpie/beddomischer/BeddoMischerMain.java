package de.lemonpie.beddomischer;

import de.lemonpie.beddomischer.http.handler.PlayerHandler;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;

public class BeddoMischerMain {
	public static void main(String[] args) {
		port(9999);

		exception(Exception.class, (exception, req, res) -> {
			exception.printStackTrace();
			Spark.halt(500, "internal error: " + exception.getLocalizedMessage());
		});


		get("/player", new PlayerHandler(), new FreeMarkerEngine());
	}
}
