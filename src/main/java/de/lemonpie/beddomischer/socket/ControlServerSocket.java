package de.lemonpie.beddomischer.socket;

import com.google.gson.Gson;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public abstract class ControlServerSocket implements Closeable {

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

	public int count() {
		return connectionHandler.getClientHandlers().size();
	}
}
