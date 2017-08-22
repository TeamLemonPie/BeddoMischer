package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerNameCommand implements Command {

	@Override
	public String name() {
		return "name";
	}

	@Override
	public void execute(CommandData command) {
		int playerId = command.getKey();
		String name = command.getValue().getAsString();

		BeddoMischerMain.getPlayer(playerId).ifPresent(p -> p.setName(name));
	}
}
