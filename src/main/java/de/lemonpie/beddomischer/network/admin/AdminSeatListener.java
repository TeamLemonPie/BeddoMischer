package de.lemonpie.beddomischer.network.admin;

import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddocommon.model.seat.SeatListener;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.network.admin.command.send.SeatPlayerSendCommand;

public class AdminSeatListener implements SeatListener
{
	@Override
	public void readerIdDidChange(Seat seat, int readerId)
	{
	}

	@Override
	public void playerIdDidChange(Seat seat, int playerId)
	{
		SeatPlayerSendCommand dataSendCommand = new SeatPlayerSendCommand(seat.getId(), playerId);
		BeddoMischerMain.getControlServerSocket().writeAll(dataSendCommand);
	}
}
