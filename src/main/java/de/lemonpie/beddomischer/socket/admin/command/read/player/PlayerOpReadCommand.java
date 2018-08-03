package de.lemonpie.beddomischer.socket.admin.command.read.player;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

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
