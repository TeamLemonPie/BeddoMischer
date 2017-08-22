package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class PlayerTwitchNameCommand implements Command {
	
	@Override
	public String name() {
		return "twitchName";
	}

	@Override
	public void execute(CommandData command) {

	}
}
