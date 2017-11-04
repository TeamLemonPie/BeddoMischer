package de.lemonpie.beddomischer.listener;

import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerState;
import de.lemonpie.beddomischer.model.card.Card;

public interface PlayerListener {
	void nameDidChange(Player player, String name);

	void twitchNameDidChange(Player player, String twitchName);

	void stateDidChange(Player player, PlayerState state);

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
