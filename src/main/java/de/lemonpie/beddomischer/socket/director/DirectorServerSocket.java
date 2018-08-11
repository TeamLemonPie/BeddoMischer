package de.lemonpie.beddomischer.socket.director;

import de.lemonpie.beddocommon.network.server.ControlServerSocket;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class DirectorServerSocket extends ControlServerSocket
{
	public DirectorServerSocket(String host, int port)
	{
		super(host, port);
	}

	public DirectorServerSocket(InetAddress host, int port)
	{
		super(host, port);
	}

	public DirectorServerSocket(InetSocketAddress socketAddress)
	{
		super(socketAddress);
	}

	@Override
	protected void init()
	{
	}
}
