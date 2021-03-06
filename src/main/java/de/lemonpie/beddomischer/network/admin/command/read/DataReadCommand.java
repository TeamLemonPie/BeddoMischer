package de.lemonpie.beddomischer.network.admin.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.network.admin.command.send.DataSendCommand;

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
