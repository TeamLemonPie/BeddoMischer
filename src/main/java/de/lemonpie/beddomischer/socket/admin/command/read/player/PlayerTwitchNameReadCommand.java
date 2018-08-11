package de.lemonpie.beddomischer.socket.admin.command.read.player;

import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.server.Command;
import de.lemonpie.beddocommon.network.server.CommandData;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class PlayerTwitchNameReadCommand implements Command
{

	@Override
	public CommandName name()
	{
		return CommandName.PLAYER_TWITCH;
	}

	@Override
	public void execute(CommandData command)
	{
		int playerId = command.getKey();
		String twitchName = command.getValue().getAsString();

		BeddoMischerMain.getPlayers().getPlayer(playerId).ifPresent(p -> p.setTwitchName(twitchName));
	}
}
