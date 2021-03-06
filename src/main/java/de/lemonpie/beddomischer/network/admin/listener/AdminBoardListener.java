package de.lemonpie.beddomischer.network.admin.listener;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.BoardListener;
import de.lemonpie.beddomischer.network.admin.command.send.CardSendCommand;

public class AdminBoardListener implements BoardListener
{

	// if card changed (by reader) send changes to beddo control
	@Override
	public void cardDidChangeAtIndex(int index, Card card)
	{
		CardSendCommand cardSendCommand = new CardSendCommand(index, card);
		BeddoMischerMain.getControlServerSocket().writeAll(cardSendCommand);
	}

	@Override
	public void readerListDidChange()
	{
		// TODO CHECK
	}

	@Override
	public void smallBlindDidChange(int newValue)
	{
	}

	@Override
	public void bigBlindDidChange(int newValue)
	{
	}

	@Override
	public void anteDidChange(int newValue)
	{
	}
}
