package de.lemonpie.beddomischer;

import de.lemonpie.beddomischer.settings.Settings;
import de.lemonpie.beddomischer.settings.SettingsHandler;
import de.lemonpie.beddomischer.socket.ControlServerSocket;

import java.io.IOException;
import java.net.Inet4Address;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static spark.Spark.port;

public class BeddoMischerMain {

	private static ControlServerSocket rfidServerSocket;
	private static ControlServerSocket controlServerSocket;

	public static void main(String[] args) {
		Path settingsPath = Paths.get("settings.properties");
		try {
			if (Files.notExists(settingsPath)) {
				SettingsHandler.saver().defaultSettings(settingsPath);
			}
			Settings settings = SettingsHandler.loader().load(settingsPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			rfidServerSocket = new ControlServerSocket(Inet4Address.getLoopbackAddress(), 9998);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			controlServerSocket = new ControlServerSocket(Inet4Address.getLoopbackAddress(), 9997);
		} catch (IOException e) {
			e.printStackTrace();
		}
		port(9999);
	}
}
