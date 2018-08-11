package de.lemonpie.beddomischer.listener;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerState;

public interface PlayerListener
{
	void nameDidChange(Player player, String name);

	void twitchNameDidChange(Player player, String twitchName);

	void stateDidChange(Player player, PlayerState state);

	void readerIdDidChange(Player player, int readerId);

	/**
	 * Update player card.
	 *
	 * @param index index of card (0 based)
	 * @param card  new card
	 */
	void cardDidChangeAtIndex(Player player, int index, Card card);

	void chipsDidChange(Player player, int chips);

	void winProbabilityDidChange(Player player, int value);
}
