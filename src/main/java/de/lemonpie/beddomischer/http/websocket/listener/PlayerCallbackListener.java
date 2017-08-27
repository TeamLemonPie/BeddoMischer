package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.card.Card;

public class PlayerCallbackListener implements PlayerListener {

	public static final String SCOPE_PLAYER = "player";
	public static final String SCOPE_CHIP = "chip";

	private Player player;
	private WebSocketHandler webSocketHandler;

	public PlayerCallbackListener(Player player, WebSocketHandler webSocketHandler) {
		this.player = player;
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void nameDidChange(String name) {
		CallbackCommand callbackCommand = new CallbackCommand(SCOPE_PLAYER, "name", player.getId(),
				new JsonPrimitive(name));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void twitchNameDidChange(String twitchName) {
		CallbackCommand callbackCommand = new CallbackCommand(SCOPE_PLAYER, "twitchName", player.getId(),
				new JsonPrimitive(twitchName));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void cardDidChangeAtIndex(int index, Card card) {
		CallbackCommand callbackCommand = new CallbackCommand(SCOPE_PLAYER, "card" + index, player.getId(),
				new JsonPrimitive(card.name()));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void chipsDidChange(int chips) {
		CallbackCommand callbackCommand = new CallbackCommand(SCOPE_CHIP, "chip", player.getId(),
				new JsonPrimitive(chips));
		webSocketHandler.sendCommand(callbackCommand);
	}
}
