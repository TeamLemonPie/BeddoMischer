package de.lemonpie.beddomischer.network.admin.command.read;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class BoardCardSetReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.BOARD_CARD;
	}

	@Override
	public void execute(CommandData command)
	{
		int key = command.getKey();
		Card card = Card.fromString(command.getValue().getAsString());

		BeddoMischerMain.getBoard().setCard(key, card);
	}
}
