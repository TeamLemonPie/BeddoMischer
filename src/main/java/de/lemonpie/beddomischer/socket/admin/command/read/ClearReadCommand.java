package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class ClearReadCommand implements Command {
    @Override
	public String name() {
		return "clear";
	}

	@Override
	public void execute(CommandData command) {

	}
}
