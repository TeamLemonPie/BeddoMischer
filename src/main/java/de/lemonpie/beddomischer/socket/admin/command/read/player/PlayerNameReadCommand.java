package de.lemonpie.beddomischer.socket.admin.command.read.player;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerNameReadCommand implements Command
{

	@Override
	public CommandName name()
	{
		return CommandName.PLAYER_NAME;
	}

	@Override
	public void execute(CommandData command)
	{
		int playerId = command.getKey();
		String name = command.getValue().getAsString();

		BeddoMischerMain.getPlayers().getPlayer(playerId).ifPresent(p -> p.setName(name));
	}
}
