package de.lemonpie.beddomischer.socket.reader;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.socket.ControlServerSocket;
import de.lemonpie.beddomischer.socket.SocketListener;
import de.lemonpie.beddomischer.socket.admin.command.send.ReaderCountSendCommand;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ReaderServerSocket extends ControlServerSocket implements SocketListener {

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
		setSocketListener(this);
		addCommand(new CardReadCommand());
	}

	@Override
	public void connectionEstablished(Socket socket) {
		BeddoMischerMain.getControlServerSocket().writeAll(new ReaderCountSendCommand(ReaderCountSendCommand.Type.ADD));
	}

	@Override
	public void connectionClosed(Socket socket) {
		BeddoMischerMain.getControlServerSocket().writeAll(new ReaderCountSendCommand(ReaderCountSendCommand.Type.REMOVE));
	}
}
