package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.BoardListener;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerList;
import de.lemonpie.beddomischer.model.PlayerState;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.winprobability.Calculation;

import java.util.List;

public class WinProbabilityPlayerListener implements PlayerListener, BoardListener {

    private WebSocketHandler webSocketHandler;

	public WinProbabilityPlayerListener(WebSocketHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
    }

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
		computeWinProperbility();
	}

    @Override
	public void cardDidChangeAtIndex(int index, Card card) {
		computeWinProperbility();
	}

	private void computeWinProperbility() {
		// Check if cards complete
		if (isPlayerSetComplete()) {
			PlayerList playerList = BeddoMischerMain.getPlayers();
			Board board = BeddoMischerMain.getBoard();
			Calculation calculation = new Calculation(playerList.getData(), board);
			List<Double> probabilities = calculation.calculate(1000);

			for (int i = 0; i < playerList.getData().size(); i++) {
				Player player = playerList.getData().get(i);
				player.setWinprobability((int) ((probabilities.get(i) * 100)));
			}
		}
	}

    private boolean isPlayerSetComplete() {
        boolean complete = true;
        for (Player player : BeddoMischerMain.getPlayers()) {
            if (player.getCardLeft() == Card.EMPTY || player.getCardRight() == Card.EMPTY) {
                complete = false;
            }
        }
        return complete;
    }

    @Override
	public void winProbabilityDidChange(Player player, int value) {
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.WINPROBABILITY, player.getId(),
				new JsonPrimitive(value));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void chipsDidChange(Player player, int chips) {
	}
}
