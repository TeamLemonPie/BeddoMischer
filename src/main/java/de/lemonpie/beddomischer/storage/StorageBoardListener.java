package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddocommon.card.Card;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.BoardListener;

public class StorageBoardListener implements BoardListener
{

	@Override
	public void cardDidChangeAtIndex(int index, Card card)
	{
		BoardSerializer.saveBoard(BeddoMischerMain.getBoard());
	}

	@Override
	public void smallBlindDidChange(int newValue)
	{
		BoardSerializer.saveBoard(BeddoMischerMain.getBoard());
	}

	@Override
	public void bigBlindDidChange(int newValue)
	{
		BoardSerializer.saveBoard(BeddoMischerMain.getBoard());
	}

	@Override
	public void anteDidChange(int newValue)
	{
		BoardSerializer.saveBoard(BeddoMischerMain.getBoard());
	}
}
