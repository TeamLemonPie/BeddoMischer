package de.lemonpie.beddomischer.listener;

import de.lemonpie.beddomischer.model.PlayerState;
import de.lemonpie.beddomischer.model.card.Card;

public interface PlayerListener {
	void nameDidChange(String name);

	void twitchNameDidChange(String twitchName);

	void stateDidChange(PlayerState state);

	/**
	 * Update player card.
	 *
	 * @param index index of card (0 based)
	 * @param card  new card
	 */
	void cardDidChangeAtIndex(int index, Card card);

	void chipsDidChange(int chips);
}
