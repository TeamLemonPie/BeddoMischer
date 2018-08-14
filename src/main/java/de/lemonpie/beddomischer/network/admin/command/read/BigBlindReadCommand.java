package de.lemonpie.beddomischer.network.admin.command.read;

import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.server.Command;
import de.lemonpie.beddocommon.network.server.CommandData;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class BigBlindReadCommand implements Command
{

	@Override
	public CommandName name()
	{
		return CommandName.BIG_BLIND;
	}

	@Override
	public void execute(CommandData command)
	{
		BeddoMischerMain.getBoard().setBigBlind(command.getValue().getAsInt());
	}

}
