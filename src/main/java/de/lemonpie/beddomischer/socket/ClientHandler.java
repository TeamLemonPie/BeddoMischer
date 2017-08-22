package de.lemonpie.beddomischer.socket;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private static Gson gson;

	static {
		gson = new Gson();
	}

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

				CommandData commandData = gson.fromJson(line, CommandData.class);

				controlServerSocket.getCommands().forEach((name, command) -> {
					if (name.equals(commandData.getCommand())) {
						command.execute(commandData);
					}
				});

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
