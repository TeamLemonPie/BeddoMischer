package de.lemonpie.beddomischer.network.admin.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;

/**
 * Forward seat player changes to admin control.
 */
public class SeatPlayerSendCommand extends CommandData
{
	public SeatPlayerSendCommand(int seatId, int newPlayerId)
	{
		super(Scope.ADMIN, CommandName.SEAT_PLAYER_ID, seatId, new JsonPrimitive(newPlayerId));
	}
}
