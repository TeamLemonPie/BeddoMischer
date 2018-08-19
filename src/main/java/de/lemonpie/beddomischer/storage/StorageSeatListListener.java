package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddocommon.model.ObservableListListener;
import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddomischer.BeddoMischerMain;
import logger.Logger;

import java.sql.SQLException;

public class StorageSeatListListener implements ObservableListListener<Seat>
{
	@Override
	public void addObjectToList(Seat obj)
	{
		if(BeddoMischerMain.getSeatDao() != null)
		{
			try
			{
				if(BeddoMischerMain.getSeatDao().queryForId(obj.getId()) == null)
				{
					BeddoMischerMain.getSeatDao().create(obj);
				}
			}
			catch(SQLException e)
			{
				Logger.error(e);
			}
		}
		obj.addListener(new StorageSeatListener());
	}

	@Override
	public void removeObjectFromList(Seat obj)
	{

	}
}
