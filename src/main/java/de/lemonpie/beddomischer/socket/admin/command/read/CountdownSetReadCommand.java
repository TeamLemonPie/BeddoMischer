package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class CountdownSetReadCommand implements Command {
	@Override
	public CommandName name() {
		return CommandName.COUNTDOWN;
	}

	@Override
	public void execute(CommandData command) {
		int minutes = command.getValue().getAsInt();

		BeddoMischerMain.setCountdownEndTime(System.currentTimeMillis() + 1000 * 60 * minutes);
	}
}
