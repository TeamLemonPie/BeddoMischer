package de.lemonpie.beddomischer.network.admin;

import de.lemonpie.beddocommon.network.server.ControlServerSocket;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class AdminServerSocket extends ControlServerSocket
{

	public AdminServerSocket(String host, int port)
	{
		super(host, port);
	}

	public AdminServerSocket(InetAddress host, int port)
	{
		super(host, port);
	}

	public AdminServerSocket(InetSocketAddress socketAddress)
	{
		super(socketAddress);
	}

	@Override
	protected void init()
	{
	}
}
