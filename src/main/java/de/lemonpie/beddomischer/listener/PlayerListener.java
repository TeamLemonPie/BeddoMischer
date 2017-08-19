package de.lemonpie.beddomischer.listener;

import de.lemonpie.beddomischer.model.card.Card;

public interface PlayerListener {
	void nameDidChange(String name);

	void twitchNameDidChange(String twitchName);

	void cardDidChangeAtIndex(int index, Card card);

	void chipsDidChange(int chips);
}
