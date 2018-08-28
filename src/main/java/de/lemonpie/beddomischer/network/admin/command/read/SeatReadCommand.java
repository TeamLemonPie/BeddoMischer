package de.lemonpie.beddomischer.network.admin.command.read;

import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;

/**
 * Key: Seat
 * Command Value: Player Id
 */
public class SeatReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.SEAT_PLAYER_ID;
	}

	@Override
	public void execute(CommandData command)
	{
		final int playerId = command.getValue().getAsInt();

		// Remove player from old seat
		BeddoMischerMain.getSeatList().getSeatByPlayerId(playerId).ifPresent(Seat::removePlayer);
		// Set player to new seat
		BeddoMischerMain.getSeatList().getObject(command.getKey()).ifPresent(seat -> {
			seat.setPlayerId(playerId);
		});
	}
}
