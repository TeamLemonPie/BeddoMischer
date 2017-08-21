package de.lemonpie.beddomischer.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private Thread thread;
	private ControlServerSocket controlServerSocket;

	private Socket socket;
	private BufferedReader inputStream;
	private PrintStream outputStream;

	public ClientHandler(Socket socket, ControlServerSocket controlServerSocket) throws IOException {
		this.controlServerSocket = controlServerSocket;

		this.socket = socket;
		this.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.outputStream = new PrintStream(socket.getOutputStream(), true);

		this.thread = new Thread(this);
	}

	public void start() {
		this.thread.start();
	}

	public void interrupt() {
		this.thread.interrupt();
	}

	public void write(String data) {
		outputStream.println(data); // AutoFlush is enable
	}

	private String line;

	@Override
	public void run() {
		try {
			while ((line = inputStream.readLine()) != null) {
				// Handle line
				System.out.printf("[%s]: %s\n", socket.getRemoteSocketAddress(), line);

				write(line);

				controlServerSocket.getCommands().forEach(command -> command.execute(line));

				if (thread.isInterrupted()) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() throws IOException {
		outputStream.close();
		inputStream.close();
		socket.close();
		System.out.printf("[%s]: Connection closed\n", socket.getRemoteSocketAddress());
	}
}
