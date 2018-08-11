package de.lemonpie.beddomischer.socket.director;

import de.lemonpie.beddomischer.socket.ControlServerSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class DirectorServerSocket extends ControlServerSocket
{
	public DirectorServerSocket(String host, int port) throws IOException
	{
		super(host, port);
	}

	public DirectorServerSocket(InetAddress host, int port) throws IOException
	{
		super(host, port);
	}

	public DirectorServerSocket(InetSocketAddress socketAddress) throws IOException
	{
		super(socketAddress);
	}

	@Override
	protected void init()
	{
	}
}
