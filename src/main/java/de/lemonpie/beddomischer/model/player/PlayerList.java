package de.lemonpie.beddomischer.model.player;

import de.lemonpie.beddocommon.model.ObservableList;

public class PlayerList extends ObservableList<Player>
{

	public Player add()
	{
		Player player = new Player();
		return add(player);
	}

	public void updateListener()
	{
		for(Player player : this)
		{
			player.setCardLeft(player.getCardLeft());
			player.setCardRight(player.getCardRight());
		}
	}
}
