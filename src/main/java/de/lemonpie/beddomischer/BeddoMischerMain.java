package de.lemonpie.beddomischer;

import de.lemonpie.beddomischer.http.handler.PlayerHandler;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
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

		Configuration freeMarkerConfiguration = new Configuration(Configuration.VERSION_2_3_0);
		freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(BeddoMischerMain.class, "/template/"));

		Spark.staticFileLocation("/public");
		get("/player", new PlayerHandler(), new FreeMarkerEngine(freeMarkerConfiguration));
	}
}
