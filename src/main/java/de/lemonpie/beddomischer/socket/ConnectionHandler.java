package de.lemonpie.beddomischer.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionHandler implements Runnable {

	private Thread thread;
	private CommandServerSocket serverSocket;

	private List<ClientHandler> clientHandlers;

	public ConnectionHandler(CommandServerSocket serverSocket) {
		this.clientHandlers = new ArrayList<>();
		this.serverSocket = serverSocket;
		this.thread = new Thread(this);
	}

	public void start() {
		thread.start();
	}

	public void interrupt() {
		thread.interrupt();
	}

	public void close() throws IOException {
		for (ClientHandler clientHandler : clientHandlers) {
			clientHandler.interrupt();
			clientHandler.close();
		}
	}

	@Override
	public void run() {
		try {
			Socket socket;
			while ((socket = serverSocket.getServerSocket().accept()) != null) {
				System.out.printf("[%s]: Connection established\n", socket.getRemoteSocketAddress());

				// Handle Client
				ClientHandler clientHandler = new ClientHandler(socket, serverSocket);
				clientHandler.start();
				clientHandlers.add(clientHandler);

				// Stop Thread
				if (thread.isInterrupted()) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
