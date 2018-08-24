package de.lemonpie.beddomischer.network.reader;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.player.Player;
import logger.Logger;

import java.util.Optional;

public class ManageCardReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.MANAGE_CARD;
	}

	@Override
	public void execute(CommandData command)
	{
		int readerId = command.getKey();
		int manageCardCode = command.getValue().getAsJsonPrimitive().getAsInt();

		if(BeddoMischerMain.getBoard().isBoardReader(readerId))
		{
			Logger.debug("Skipping manageCard with ID '" + manageCardCode + "' because reader with ID '" + readerId + "' is a board reader.");
			return;
		}

		BeddoMischerMain.getSeatList().getSeatByReader(readerId).ifPresent(seat -> {
			final Optional<Player> playerOptional = BeddoMischerMain.getPlayers().getObject(seat.getPlayerId());
			playerOptional.ifPresent(player -> player.setManageCardId(manageCardCode));
		});
	}
}
