package de.lemonpie.beddomischer.network.reader;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;

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

		BeddoMischerMain.getSeatList().forEach(seat -> {
			if(seat.getReaderId() == readerId)
			{
				BeddoMischerMain.getPlayers().getObject(seat.getPlayerId()).ifPresent(player -> player.setCard(card));
			}
		});

		if(BeddoMischerMain.getBoard().getReaderIds().contains(readerId))
		{
			BeddoMischerMain.getBoard().setCard(card);
		}
	}
}
