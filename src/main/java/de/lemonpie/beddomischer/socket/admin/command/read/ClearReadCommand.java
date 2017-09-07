package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.reader.BoardCardReader;
import de.lemonpie.beddomischer.model.reader.PlayerCardReader;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;
import de.lemonpie.beddomischer.socket.reader.ClearSendCommand;

public class ClearReadCommand implements Command {
    @Override
    public CommandName name() {
        return CommandName.CLEAR;
    }

	@Override
	public void execute(CommandData command) {
		int key = command.getKey();

		// All Reader
		if (key == -1) {
			// Clear local data
			for (Player player : BeddoMischerMain.getPlayers()) {
				player.clearCards();
			}
			Board board = BeddoMischerMain.getBoard();
			board.clearCards();
		} else {
			BeddoMischerMain.getCardReader(key).ifPresent(reader -> {
				if (reader instanceof PlayerCardReader) {
					int playerId = ((PlayerCardReader) reader).getPlayerId();
                    BeddoMischerMain.getPlayers().getPlayer(playerId).ifPresent(Player::clearCards);
				} else if (reader instanceof BoardCardReader) {
					Board board = BeddoMischerMain.getBoard();
					board.setCard(Card.EMPTY);
				}
			});
		}

        ClearSendCommand forwardCommandData = new ClearSendCommand(key);
        BeddoMischerMain.getRfidServerSocket().writeAll(forwardCommandData);
	}
}
