package de.lemonpie.beddomischer.socket.reader;

import de.lemonpie.beddomischer.socket.ControlServerSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ReaderServerSocket extends ControlServerSocket {

	public ReaderServerSocket(String host, int port) throws IOException {
		super(host, port);
	}

	public ReaderServerSocket(InetAddress host, int port) throws IOException {
		super(host, port);
	}

	public ReaderServerSocket(InetSocketAddress socketAddress) throws IOException {
		super(socketAddress);
	}

	@Override
	protected void init() {
		addCommand(new CardCommand());
	}
}
