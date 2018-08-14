package de.lemonpie.beddomischer.network.admin.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
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
