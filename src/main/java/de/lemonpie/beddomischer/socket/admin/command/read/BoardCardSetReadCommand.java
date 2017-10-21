package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class BoardCardSetReadCommand implements Command {
	@Override
	public CommandName name() {
		return CommandName.BOARD_CARD;
	}

	@Override
	public void execute(CommandData command) {
		int key = command.getKey();
		Card card = Card.fromString(command.getValue().getAsString());

		BeddoMischerMain.getBoard().setCard(key, card);
	}
}
