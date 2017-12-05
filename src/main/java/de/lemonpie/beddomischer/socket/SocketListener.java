package de.lemonpie.beddomischer.socket;

import java.net.Socket;

public interface SocketListener {

	void connectionEstablished(Socket socket);

	void connectionClosed(Socket socket);
}
