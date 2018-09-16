package de.lemonpie.beddomischer.network.reader;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.player.Player;

import java.util.Optional;

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
		byte readerId = (byte) command.getKey();
		String cardCode = command.getValue().getAsJsonPrimitive().getAsString();
		Card card = Card.fromString(cardCode);

		BeddoMischerMain.getSeatList().getSeatByReader(readerId).ifPresent(seat -> {
			final Optional<Player> playerOptional = BeddoMischerMain.getPlayers().getObject(seat.getPlayerId());
			playerOptional.ifPresent(player -> player.setCard(card));
		});

		if(BeddoMischerMain.getBoard().isBoardReader(readerId))
		{
			BeddoMischerMain.getBoard().setCard(card);
		}
	}
}
