package de.lemonpie.beddomischer.network.reader;

import de.lemonpie.beddocommon.network.server.ControlServerSocket;
import de.lemonpie.beddocommon.network.server.SocketListener;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.network.admin.command.send.ReaderCountSendCommand;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ReaderServerSocket extends ControlServerSocket implements SocketListener
{

	public ReaderServerSocket(String host, int port)
	{
		super(host, port);
	}

	public ReaderServerSocket(InetAddress host, int port)
	{
		super(host, port);
	}

	public ReaderServerSocket(InetSocketAddress socketAddress)
	{
		super(socketAddress);
	}

	@Override
	protected void init()
	{
		setSocketListener(this);
	}

	@Override
	public void connectionEstablished(Socket socket)
	{
		BeddoMischerMain.getControlServerSocket().writeAll(new ReaderCountSendCommand(ReaderCountSendCommand.Type.ADD));
	}

	@Override
	public void connectionClosed(Socket socket)
	{
		BeddoMischerMain.getControlServerSocket().writeAll(new ReaderCountSendCommand(ReaderCountSendCommand.Type.REMOVE));
	}
}
