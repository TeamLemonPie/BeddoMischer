package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;
import de.lemonpie.beddomischer.socket.reader.ClearSendCommand;
import de.lemonpie.beddomischer.validator.CardValidator;

import java.util.stream.Stream;

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
			CardValidator.getInstance().clear();

			ClearSendCommand forwardCommandData = new ClearSendCommand(key);

			if (BeddoMischerMain.getRfidServerSocket() != null) {
				BeddoMischerMain.getRfidServerSocket().writeAll(forwardCommandData);
			}
		} else if (key == -2) {
			Board board = BeddoMischerMain.getBoard();
			Stream.of(board.getCards()).forEach(CardValidator.getInstance()::clear);
			board.clearCards();

			BeddoMischerMain.getBoard().getReaderIds().forEach(r -> {
				ClearSendCommand forwardCommandData = new ClearSendCommand(r);

				if (BeddoMischerMain.getRfidServerSocket() != null) {
					BeddoMischerMain.getRfidServerSocket().writeAll(forwardCommandData);
				}
			});
		} else {
			BeddoMischerMain.getPlayers().forEach(player -> {
				if (player.getReaderId() == key) {
					CardValidator.getInstance().clear(player.getCardLeft(), player.getCardRight());
					player.clearCards();
				}
			});

			ClearSendCommand forwardCommandData = new ClearSendCommand(key);
			if (BeddoMischerMain.getRfidServerSocket() != null) {
				BeddoMischerMain.getRfidServerSocket().writeAll(forwardCommandData);
			}
		}
	}
}
