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
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.winprobability.Calculation;

import java.util.List;

public class WinprobabilityPlayerListener implements PlayerListener, BoardListener {

    private WebSocketHandler webSocketHandler;

    public WinprobabilityPlayerListener(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @Override
    public void nameDidChange(String name) {
    }

    @Override
    public void twitchNameDidChange(String twitchName) {
    }

    @Override
    public void hideDidChange(boolean hide) {
    }

    // Method from Player and Board Listener
    @Override
    public void cardDidChangeAtIndex(int index, Card card) {
        // Check if cards complete
        if (isPlayerSetComplete()) {
            PlayerList playerList = BeddoMischerMain.getPlayers();
            Board board = BeddoMischerMain.getBoard();
            Calculation calculation = new Calculation(playerList.getData(), board);
            List<Double> probabilities = calculation.calculate(1);

            for (int i = 0; i < playerList.getData().size(); i++) {
                Player player = playerList.getData().get(i);
                player.setWinprobability(probabilities.get(i));

                CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.WINPROBABILITY, player.getId(),
                        new JsonPrimitive(probabilities.get(i)));
                webSocketHandler.sendCommand(callbackCommand);
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
    public void chipsDidChange(int chips) {
    }
}
