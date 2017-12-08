package de.lemonpie.beddomischer.socket;

import com.google.gson.Gson;
import de.lemonpie.beddomischer.CommandName;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public abstract class ControlServerSocket implements Closeable {

	// Command Name, Command Implementation
    private Map<CommandName, Command> commands;

	private ServerSocket serverSocket;
	private ConnectionHandler connectionHandler;

	private SocketListener socketListener;

	public ControlServerSocket(String host, int port) throws IOException {
		this(new InetSocketAddress(host, port));
	}

	public ControlServerSocket(InetAddress host, int port) throws IOException {
		this(new InetSocketAddress(host, port));
	}

	public ControlServerSocket(InetSocketAddress socketAddress) throws IOException {
		commands = new HashMap<>();
		serverSocket = new ServerSocket();
		serverSocket.bind(socketAddress);

		init();

		connectionHandler = new ConnectionHandler(this);
		connectionHandler.start();
	}

	protected abstract void init();

	public SocketListener getSocketListener() {
		return socketListener;
	}

	public void setSocketListener(SocketListener socketListener) {
		this.socketListener = socketListener;
	}

	public void writeAll(CommandData command) {
        Gson gson = new Gson();
        writeAll(gson.toJson(command));
    }

	public void writeAll(String data) {
		for (ClientHandler client : connectionHandler.getClientHandlers()) {
			client.write(data);
		}
	}

	public void addCommand(Command command) {
		this.commands.put(command.name(), command);
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

	ConnectionHandler getConnectionHandler() {
		return connectionHandler;
	}

	public Map<CommandName, Command> getCommands() {
        return commands;
	}

	public int count() {
		return connectionHandler.getClientHandlers().size();
	}
}
