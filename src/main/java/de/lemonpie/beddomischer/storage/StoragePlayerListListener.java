package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddocommon.model.ObservableListListener;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.player.Player;
import de.tobias.logger.Logger;

import java.sql.SQLException;

public class StoragePlayerListListener implements ObservableListListener<Player>
{
	@Override
	public void addObjectToList(Player player)
	{
		if(BeddoMischerMain.getPlayerDao() != null)
		{
			try
			{
				if(BeddoMischerMain.getPlayerDao().queryForId(player.getId()) == null)
				{
					BeddoMischerMain.getPlayerDao().create(player);
				}
			}
			catch(SQLException e)
			{
				Logger.error(e);
			}
		}
		player.addListener(new StoragePlayerListener());
	}

	@Override
	public void removeObjectFromList(Player player)
	{
		try
		{
			BeddoMischerMain.getPlayerDao().delete(player);
		}
		catch(SQLException e)
		{
			Logger.error(e);
		}
	}
}
