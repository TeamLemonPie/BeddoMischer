package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerTwitchNameCommand implements Command {
	
	@Override
	public String name() {
		return "twitchName";
	}

	@Override
	public void execute(CommandData command) {
		int playerId = command.getKey();
		String twitchName = command.getValue().getAsString();

		BeddoMischerMain.getPlayer(playerId).ifPresent(p -> p.setTwitchName(twitchName));
	}
}
