package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddocommon.card.Card;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.BoardListener;
import de.lemonpie.beddomischer.socket.admin.command.send.CardSendCommand;

public class AdminBoardListener implements BoardListener
{

	// if card changed (by reader) send changes to admin
	@Override
	public void cardDidChangeAtIndex(int index, Card card)
	{
		CardSendCommand cardSendCommand = new CardSendCommand(index, card);
		BeddoMischerMain.getControlServerSocket().writeAll(cardSendCommand);
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
