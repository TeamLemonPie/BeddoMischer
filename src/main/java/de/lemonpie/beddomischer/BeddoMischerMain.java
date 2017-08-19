package de.lemonpie.beddomischer;

import de.lemonpie.beddomischer.socket.CommandServerSocket;

import java.io.IOException;
import java.net.Inet4Address;

import static spark.Spark.port;

public class BeddoMischerMain {

	private static CommandServerSocket rfidServerSocket;
	private static CommandServerSocket controlServerSocket;

	public static void main(String[] args) {
		try {
			rfidServerSocket = new CommandServerSocket(Inet4Address.getLoopbackAddress(), 9998);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			controlServerSocket = new CommandServerSocket(Inet4Address.getLoopbackAddress(), 9997);
		} catch (IOException e) {
			e.printStackTrace();
		}
		port(9999);
	}
}
