package de.lemonpie.beddomischer.socket.reader;

import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

/**
 * Command to receive detected cards from reader.
 * <code>key = reader id</code>
 * <code>value = card-code</code>
 */
public class CardCommand implements Command {
	@Override
	public String name() {
		return "card";
	}

	@Override
	public void execute(CommandData command) {

	}
}
