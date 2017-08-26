package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.card.BlankCard;
import de.lemonpie.beddomischer.model.reader.BoardCardReader;
import de.lemonpie.beddomischer.model.reader.PlayerCardReader;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class ClearReadCommand implements Command {
    @Override
	public String name() {
		return "clear";
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
					BeddoMischerMain.getPlayer(playerId).ifPresent(player -> {
						player.clearCards();
					});
				} else if (reader instanceof BoardCardReader) {
					int boardIndex = ((BoardCardReader) reader).getIndex();
					Board board = BeddoMischerMain.getBoard();
					board.setCard(boardIndex, new BlankCard());
				}
			});
		}

		CommandData forwardCommandData = new CommandData("reader", "clear", key, null);
		BeddoMischerMain.getRfidServerSocket().writeAll(forwardCommandData);
	}
}
