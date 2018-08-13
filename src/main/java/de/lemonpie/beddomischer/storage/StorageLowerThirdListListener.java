package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddocommon.model.ObservableListListener;
import de.lemonpie.beddocommon.model.lowerthird.LowerThird;
import de.lemonpie.beddomischer.BeddoMischerMain;
import logger.Logger;

import java.sql.SQLException;

public class StorageLowerThirdListListener implements ObservableListListener<LowerThird>
{
	@Override
	public void addObjectToList(LowerThird player)
	{
		if(BeddoMischerMain.getLowerThirdDao() != null)
		{
			try
			{
				if(BeddoMischerMain.getLowerThirdDao().queryForId(player.getId()) == null)
				{
					BeddoMischerMain.getLowerThirdDao().create(player);
				}
			}
			catch(SQLException e)
			{
				Logger.error(e);
			}
		}
	}

	@Override
	public void removeObjectFromList(LowerThird player)
	{
		try
		{
			BeddoMischerMain.getLowerThirdDao().delete(player);
		}
		catch(SQLException e)
		{
			Logger.error(e);
		}
	}
}
