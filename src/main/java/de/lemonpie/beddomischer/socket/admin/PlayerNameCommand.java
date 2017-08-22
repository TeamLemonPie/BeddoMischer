package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerNameCommand implements Command {

	@Override
	public String name() {
		return "name";
	}

	@Override
	public void execute(CommandData command) {

	}
}
