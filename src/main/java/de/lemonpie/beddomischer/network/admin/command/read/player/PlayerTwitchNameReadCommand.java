package de.lemonpie.beddomischer.network.admin.command.read.player;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
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

		BeddoMischerMain.getPlayers().getObject(playerId).ifPresent(p -> p.setTwitchName(twitchName));
	}
}
