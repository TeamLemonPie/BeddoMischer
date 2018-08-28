package de.lemonpie.beddomischer.network.admin.listener;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.player.Player;
import de.lemonpie.beddomischer.model.player.PlayerState;
import de.lemonpie.beddomischer.network.admin.command.send.CardSendCommand;
import de.lemonpie.beddomischer.network.admin.command.send.PlayerWinProbabilitySendCommand;

public class AdminPlayerListener implements PlayerListener
{

	@Override
	public void nameDidChange(Player player, String name)
	{
	}

	@Override
	public void twitchNameDidChange(Player player, String twitchName)
	{
	}

	@Override
	public void stateDidChange(Player player, PlayerState state)
	{
	}

	@Override
	public void cardDidChangeAtIndex(Player player, int index, Card card)
	{
		CardSendCommand cardSendCommand = new CardSendCommand(player.getId(), index, card);
		BeddoMischerMain.getControlServerSocket().writeAll(cardSendCommand);
	}

	@Override
	public void chipsDidChange(Player player, int chips)
	{
	}

	@Override
	public void winProbabilityDidChange(Player player, int value)
	{
		PlayerWinProbabilitySendCommand command = new PlayerWinProbabilitySendCommand(player, value);
		BeddoMischerMain.getControlServerSocket().writeAll(command);
	}

	@Override
	public void isHighlightedDidChange(Player player, boolean value)
	{
	}

	@Override
	public void manageCardIndexDidChange(Player player, int value)
	{
	}
}
