package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

/**
 * Configure Reader.
 * <code>key = reader id</code>
 * <code>value = {type, index}</code>
 */
public class ReaderCommand implements Command {
	@Override
	public String name() {
		return "reader";
	}

	@Override
	public void execute(CommandData command) {

	}
}
