package de.lemonpie.beddomischer.socket.reader;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.reader.BoardCardReader;
import de.lemonpie.beddomischer.model.reader.PlayerCardReader;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

import java.util.Optional;

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
        int readerId = command.getKey();
        String cardCode = command.getValue().getAsJsonPrimitive().getAsString();
		Card card = Card.fromString(cardCode);

        BeddoMischerMain.getCardReaders().stream().filter(r -> r.getReaderId() == readerId).forEach(reader -> {
            if (reader instanceof BoardCardReader) {
				int index = ((BoardCardReader) reader).getIndex();
				BeddoMischerMain.getBoard().setCard(index, card);
			} else if (reader instanceof PlayerCardReader) {
				PlayerCardReader playerCardReader = (PlayerCardReader) reader;
				Optional<Player> player = BeddoMischerMain.getPlayers().getPlayer(playerCardReader.getPlayerId());
				player.ifPresent(p -> {
                    p.setCard(card);
                });
			}
		});
	}
}
