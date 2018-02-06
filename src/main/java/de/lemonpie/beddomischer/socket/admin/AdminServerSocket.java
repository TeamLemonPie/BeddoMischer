package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.socket.ControlServerSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class AdminServerSocket extends ControlServerSocket {

	public AdminServerSocket(String host, int port) throws IOException {
		super(host, port);
	}

	public AdminServerSocket(InetAddress host, int port) throws IOException {
		super(host, port);
	}

	public AdminServerSocket(InetSocketAddress socketAddress) throws IOException {
		super(socketAddress);
	}

	@Override
	protected void init() {
	}
}
