package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddocommon.model.ObservableListListener;
import de.lemonpie.beddocommon.model.lowerthird.LowerThird;
import de.lemonpie.beddomischer.BeddoMischerMain;
import logger.Logger;

import java.sql.SQLException;

public class StorageLowerThirdListListener implements ObservableListListener<LowerThird>
{
	@Override
	public void addObjectToList(LowerThird lowerThird)
	{
		if(BeddoMischerMain.getLowerThirdDao() != null)
		{
			try
			{
				if(BeddoMischerMain.getLowerThirdDao().queryForId(lowerThird.getId()) == null)
				{
					BeddoMischerMain.getLowerThirdDao().create(lowerThird);
				}
			}
			catch(SQLException e)
			{
				Logger.error(e);
			}
		}
	}

	@Override
	public void removeObjectFromList(LowerThird lowerThird)
	{
		try
		{
			BeddoMischerMain.getLowerThirdDao().delete(lowerThird);
		}
		catch(SQLException e)
		{
			Logger.error(e);
		}
	}
}
