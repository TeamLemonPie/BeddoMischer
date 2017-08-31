package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerTwitchNameReadCommand implements Command {

    @Override
    public CommandName name() {
        return CommandName.PLAYER_TWITCH;
    }

	@Override
	public void execute(CommandData command) {
		int playerId = command.getKey();
		String twitchName = command.getValue().getAsString();

		BeddoMischerMain.getPlayers().getPlayer(playerId).ifPresent(p -> p.setTwitchName(twitchName));
	}
}
