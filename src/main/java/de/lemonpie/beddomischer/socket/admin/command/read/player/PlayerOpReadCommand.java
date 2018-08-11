package de.lemonpie.beddomischer.socket.admin.command.read.player;

import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.server.Command;
import de.lemonpie.beddocommon.network.server.CommandData;
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
			BeddoMischerMain.getPlayers().getPlayer(playerId).ifPresent(player -> BeddoMischerMain.getPlayers().remove(player));
		}
	}
}
