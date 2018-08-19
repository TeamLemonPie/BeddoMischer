package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.player.Player;
import de.lemonpie.beddomischer.model.player.PlayerState;
import logger.Logger;

import java.sql.SQLException;

public class StoragePlayerListener implements PlayerListener
{

	@Override
	public void nameDidChange(Player player, String name)
	{
		save(player);
	}

	@Override
	public void twitchNameDidChange(Player player, String twitchName)
	{
		save(player);
	}

	@Override
	public void stateDidChange(Player player, PlayerState state)
	{
		save(player);
	}

	@Override
	public void cardDidChangeAtIndex(Player player, int index, Card card)
	{
		save(player);
	}

	@Override
	public void chipsDidChange(Player player, int chips)
	{
		save(player);
	}

	@Override
	public void winProbabilityDidChange(Player player, int value)
	{
	}

	@Override
	public void isHighlightedDidChange(Player player, boolean value)
	{
		save(player);
	}

	private void save(Player player)
	{
		try
		{
			BeddoMischerMain.getPlayerDao().update(player);
		}
		catch(SQLException e)
		{
			Logger.error(e);
		}
	}
}
