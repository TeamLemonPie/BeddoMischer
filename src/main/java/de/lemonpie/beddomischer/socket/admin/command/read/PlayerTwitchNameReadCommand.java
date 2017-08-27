package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerTwitchNameReadCommand implements Command {

    @Override
	public String name() {
		return "twitchName";
	}

	@Override
	public void execute(CommandData command) {
		int playerId = command.getKey();
		String twitchName = command.getValue().getAsString();

		BeddoMischerMain.getPlayers().getPlayer(playerId).ifPresent(p -> p.setTwitchName(twitchName));
	}
}
