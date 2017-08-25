package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.PlayerListListener;
import de.lemonpie.beddomischer.model.Player;

public class PlayerListWebListener implements PlayerListListener {

    private WebSocketHandler webSocket;

    public PlayerListWebListener(WebSocketHandler webSocket) {
        this.webSocket = webSocket;
    }

    @Override
    public void addPlayer(Player player) {
        player.addListener(new PlayerCallbackListener(player, webSocket));
        webSocket.sendCommand(new CallbackCommand("player", "player-op", player.getId(),
                new JsonPrimitive("add")));
    }

    @Override
    public void removePlayer(Player player) {
        webSocket.sendCommand(new CallbackCommand("player", "player-op", player.getId(),
                new JsonPrimitive("remove")));
    }
}
