package de.lemonpie.beddomischer.listener;

import de.lemonpie.beddomischer.model.card.Card;

public interface BoardListener {
	void cardDidChangeAtIndex(int index, Card card);
}
