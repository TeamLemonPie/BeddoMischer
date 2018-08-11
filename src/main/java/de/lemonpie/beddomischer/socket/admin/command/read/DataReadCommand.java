package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.server.Command;
import de.lemonpie.beddocommon.network.server.CommandData;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.socket.admin.command.send.DataSendCommand;

public class DataReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.DATA;
	}

	@Override
	public void execute(CommandData command)
	{
		BeddoMischerMain.getControlServerSocket().writeAll(new DataSendCommand());
	}
}
