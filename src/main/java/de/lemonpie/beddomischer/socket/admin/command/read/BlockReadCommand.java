package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class BlockReadCommand implements Command {
	@Override
	public CommandName name() {
		return CommandName.BLOCK;
	}

	@Override
	public void execute(CommandData command) {

	}
}
