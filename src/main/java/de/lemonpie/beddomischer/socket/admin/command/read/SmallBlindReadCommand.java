package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.server.Command;
import de.lemonpie.beddocommon.network.server.CommandData;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class SmallBlindReadCommand implements Command
{

	@Override
	public CommandName name()
	{
		return CommandName.SMALL_BLIND;
	}

	@Override
	public void execute(CommandData command)
	{
		BeddoMischerMain.getBoard().setSmallBlind(command.getValue().getAsInt());
	}

}
