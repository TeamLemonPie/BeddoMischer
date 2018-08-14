package de.lemonpie.beddomischer.network.admin.command.read.player;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.player.PlayerState;

public class PlayerStateReadCommand implements Command
{

	@Override
	public CommandName name()
	{
		return CommandName.PLAYER_STATE;
	}

	@Override
	public void execute(CommandData command)
	{
		int playerId = command.getKey();
		PlayerState state = PlayerState.valueOf(command.getValue().getAsString());

		BeddoMischerMain.getPlayers().getObject(playerId).ifPresent(p -> p.setPlayerState(state));
	}
}
