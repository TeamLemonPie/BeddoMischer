package de.lemonpie.beddomischer.network.admin.command.read.player;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class PlayerOpReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.PLAYER_OP;
	}

	@Override
	public void execute(CommandData command)
	{
		String op = command.getValue().getAsString();
		if(op.equals("add"))
		{
			BeddoMischerMain.getPlayers().add();
		}
		else if(op.equals("remove"))
		{
			int playerId = command.getKey();
			BeddoMischerMain.getPlayers().getObject(playerId).ifPresent(player -> BeddoMischerMain.getPlayers().remove(player));
		}
	}
}
