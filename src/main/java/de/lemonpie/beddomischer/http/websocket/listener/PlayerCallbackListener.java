package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerState;
import de.lemonpie.beddomischer.model.card.Card;

public class PlayerCallbackListener implements PlayerListener {

	private Player player;
	private WebSocketHandler webSocketHandler;

	public PlayerCallbackListener(Player player, WebSocketHandler webSocketHandler) {
		this.player = player;
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void nameDidChange(Player player, String name) {
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.PLAYER_NAME, player.getId(),
				new JsonPrimitive(name));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void twitchNameDidChange(Player player, String twitchName) {
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.PLAYER_TWITCH, player.getId(),
				new JsonPrimitive(twitchName));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void stateDidChange(Player player, PlayerState state) {
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.PLAYER_STATE, player.getId(),
				new JsonPrimitive(state.name()));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void readerIdDidChange(Player player, int readerId) {
	}

	@Override
	public void cardDidChangeAtIndex(Player player, int index, Card card) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("card", card.getName());
		jsonObject.addProperty("index", index);
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.CARD, player.getId(), jsonObject);
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void chipsDidChange(Player player, int chips) {
		CallbackCommand callbackCommand = new CallbackCommand(Scope.CHIP, CommandName.PLAYER_CHIP, player.getId(),
				new JsonPrimitive(chips));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void winProbabilityDidChange(Player player, int value) {
		// Handled in WinProbabilityPlayerListener
	}
}
