package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.model.PlayerState;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerStateReadCommand implements Command {

    @Override
    public CommandName name() {
		return CommandName.PLAYER_STATE;
	}

    @Override
    public void execute(CommandData command) {
        int playerId = command.getKey();
		PlayerState state = PlayerState.valueOf(command.getValue().getAsString());

		BeddoMischerMain.getPlayers().getPlayer(playerId).ifPresent(p -> p.setPlayerState(state));
	}
}
