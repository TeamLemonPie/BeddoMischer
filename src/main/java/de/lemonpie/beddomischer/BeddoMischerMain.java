package de.lemonpie.beddomischer;

import de.lemonpie.beddomischer.settings.Settings;
import de.lemonpie.beddomischer.settings.SettingsHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static spark.Spark.port;

public class BeddoMischerMain {
	public static void main(String[] args) {
		port(9999);

		Path settingsPath = Paths.get("settings.properties");
		try {
			if (Files.notExists(settingsPath)) {
				SettingsHandler.saver().defaultSettings(settingsPath);
			}
			Settings settings = SettingsHandler.loader().load(settingsPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
