package de.lemonpie.beddomischer.network.director.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.network.director.command.send.DirectorDataSendCommand;

public class DirectorDataReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.DATA;
	}

	@Override
	public void execute(CommandData command)
	{
		BeddoMischerMain.getDirectorServerSocket().writeAll(new DirectorDataSendCommand());
	}
}

