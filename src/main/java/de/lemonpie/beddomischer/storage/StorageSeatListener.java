package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddocommon.model.seat.SeatListener;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.tobias.logger.Logger;

import java.sql.SQLException;

public class StorageSeatListener implements SeatListener
{
	@Override
	public void readerIdDidChange(Seat seat, int readerId)
	{
		save(seat);
	}

	@Override
	public void playerIdDidChange(Seat seat, int playerId)
	{
		save(seat);
	}

	private void save(Seat seat)
	{
		try
		{
			BeddoMischerMain.getSeatDao().update(seat);
		}
		catch(SQLException e)
		{
			Logger.error(e);
		}
	}
}
