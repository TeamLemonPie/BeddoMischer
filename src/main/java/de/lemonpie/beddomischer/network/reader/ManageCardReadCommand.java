package de.lemonpie.beddomischer.network.reader;

import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.player.Player;
import de.tobias.logger.Logger;

import java.util.Iterator;
import java.util.Optional;

public class ManageCardReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.MANAGE_CARD;
	}

	@Override
	public synchronized void execute(CommandData command)
	{
		int readerId = command.getKey();
		int manageCardCode = command.getValue().getAsJsonPrimitive().getAsInt();

		if(BeddoMischerMain.getBoard().isBoardReader(readerId))
		{
			Logger.debug("Skipping manageCard with ID '" + manageCardCode + "' because reader with ID '" + readerId + "' is a board reader.");
			return;
		}

		BeddoMischerMain.getSeatList().getSeatByReader(readerId).ifPresent(seat -> {
			try
			{
				final Iterator<Player> iterator = BeddoMischerMain.getPlayers().iterator();
				//noinspection WhileLoopReplaceableByForEach
				while(iterator.hasNext())
				{
					Player currentPlayer = iterator.next();
					if(currentPlayer.getManageCardId() == manageCardCode)
					{
						//remove player from current seat
						Optional<Seat> previousSeatOptional = BeddoMischerMain.getSeatList().getSeatByPlayerId(currentPlayer.getId());
						previousSeatOptional.ifPresent(previousSeat -> previousSeat.setPlayerId(-1));

						//add player to new seat
						Optional<Seat> newSeatOptional = BeddoMischerMain.getSeatList().getSeatByReader(readerId);
						newSeatOptional.ifPresent(newSeat -> newSeat.setPlayerId(currentPlayer.getId()));
					}
				}
			}
			catch(Exception e)
			{
				Logger.error(e);
			}
		});
	}
}
