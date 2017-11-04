package de.lemonpie.beddomischer.socket.admin;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerState;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.socket.admin.command.send.CardSendCommand;
import de.lemonpie.beddomischer.socket.admin.command.send.PlayerWinProbabilitySendCommand;

public class AdminPlayerListener implements PlayerListener {

	@Override
	public void nameDidChange(Player player, String name) {
	}

	@Override
	public void twitchNameDidChange(Player player, String twitchName) {
	}

	@Override
	public void stateDidChange(Player player, PlayerState state) {
	}

	@Override
	public void cardDidChangeAtIndex(Player player, int index, Card card) {
		CardSendCommand cardSendCommand = new CardSendCommand(player.getId(), index, card);
		BeddoMischerMain.getControlServerSocket().writeAll(cardSendCommand);
	}

	@Override
	public void chipsDidChange(Player player, int chips) {
	}

	@Override
	public void winProbabilityDidChange(Player player, double value) {
		PlayerWinProbabilitySendCommand command = new PlayerWinProbabilitySendCommand(player, value);
		BeddoMischerMain.getControlServerSocket().writeAll(command);
	}
}
