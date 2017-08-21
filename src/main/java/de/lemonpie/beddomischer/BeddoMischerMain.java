package de.lemonpie.beddomischer;

import de.lemonpie.beddomischer.socket.ControlServerSocket;

import java.io.IOException;
import java.net.Inet4Address;

import static spark.Spark.port;

public class BeddoMischerMain {

	private static ControlServerSocket rfidServerSocket;
	private static ControlServerSocket controlServerSocket;

	public static void main(String[] args) {
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
