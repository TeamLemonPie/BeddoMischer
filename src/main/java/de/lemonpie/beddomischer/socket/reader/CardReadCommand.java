package de.lemonpie.beddomischer.socket.reader;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

/**
 * Command to receive detected cards from reader.
 * <code>key = reader id</code>
 * <code>value = card-code</code>
 */
public class CardReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.CARD;
	}

	@Override
	public void execute(CommandData command)
	{
		int readerId = command.getKey();
		String cardCode = command.getValue().getAsJsonPrimitive().getAsString();
		Card card = Card.fromString(cardCode);

		BeddoMischerMain.getPlayers().forEach(player -> {
			if(player.getReaderId() == readerId)
			{
				player.setCard(card);
			}
		});

		if(BeddoMischerMain.getBoard().getReaderIds().contains(readerId))
		{
			BeddoMischerMain.getBoard().setCard(card);
		}
	}
}
