package de.lemonpie.beddomischer.listener;

import de.lemonpie.beddocommon.model.card.Card;

public interface BoardListener
{
	void cardDidChangeAtIndex(int index, Card card);

	void smallBlindDidChange(int newValue);

	void bigBlindDidChange(int newValue);

	void anteDidChange(int newValue);
}
