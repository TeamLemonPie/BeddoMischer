package de.lemonpie.beddomischer.listener;

import de.lemonpie.beddomischer.model.card.Card;

public interface PlayerListener {
	void nameDidChange(String name);

	void twitchNameDidChange(String twitchName);

    void hideDidChange(boolean hide);

	/**
	 * Update player card.
	 *
	 * @param index index of card (0 based)
	 * @param card  new card
	 */
	void cardDidChangeAtIndex(int index, Card card);

	void chipsDidChange(int chips);
}
