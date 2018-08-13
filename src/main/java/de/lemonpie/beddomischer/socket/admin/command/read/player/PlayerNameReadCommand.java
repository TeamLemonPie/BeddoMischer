package de.lemonpie.beddomischer.socket.admin.command.read.player;

import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.server.Command;
import de.lemonpie.beddocommon.network.server.CommandData;
import de.lemonpie.beddomischer.BeddoMischerMain;

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

		BeddoMischerMain.getPlayers().getObject(playerId).ifPresent(p -> p.setName(name));
	}
}
