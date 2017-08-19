package de.lemonpie.beddomischer.socket;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class CommandServerSocket implements Closeable {

	private List<Command> commands;

	private ServerSocket serverSocket;
	private ConnectionHandler connectionHandler;

	public CommandServerSocket(String host, int port) throws IOException {
		this(new InetSocketAddress(host, port));
	}

	public CommandServerSocket(InetAddress host, int port) throws IOException {
		this(new InetSocketAddress(host, port));
	}

	public CommandServerSocket(InetSocketAddress socketAddress) throws IOException {
		commands = new ArrayList<>();
		serverSocket = new ServerSocket();
		serverSocket.bind(socketAddress);

		connectionHandler = new ConnectionHandler(this);
		connectionHandler.start();
	}

	public void addCommand(Command command) {
		this.commands.add(command);
	}

	@Override
	public void close() throws IOException {
		if (connectionHandler != null) {
			connectionHandler.interrupt();
			connectionHandler.close();
		}

		if (serverSocket != null) {
			serverSocket.close();
			serverSocket = null;
		}
	}

	ServerSocket getServerSocket() {
		return serverSocket;
	}

	public List<Command> getCommands() {
		return commands;
	}
}
