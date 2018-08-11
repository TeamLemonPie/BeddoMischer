package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerState;

public class PlayerCallbackListener implements PlayerListener
{

	private WebSocketHandler webSocketHandler;

	public PlayerCallbackListener(WebSocketHandler webSocketHandler)
	{
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void nameDidChange(Player player, String name)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.PLAYER_NAME, player.getId(),
				new JsonPrimitive(name));
		webSocketHandler.sendCommand(callbackCommand);

		CallbackCommand callbackFeedbackCommand = new CallbackCommand(Scope.PLAYER_FEEDBACK, CommandName.PLAYER_NAME, player.getId(),
				new JsonPrimitive(name));
		webSocketHandler.sendCommand(callbackFeedbackCommand);
	}

	@Override
	public void twitchNameDidChange(Player player, String twitchName)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.PLAYER_TWITCH, player.getId(),
				new JsonPrimitive(twitchName));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void stateDidChange(Player player, PlayerState state)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.PLAYER_STATE, player.getId(),
				new JsonPrimitive(state.name()));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void readerIdDidChange(Player player, int readerId)
	{
	}

	@Override
	public void cardDidChangeAtIndex(Player player, int index, Card card)
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("card", card.getName());
		jsonObject.addProperty("index", index);
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER, CommandName.CARD, player.getId(), jsonObject);
		webSocketHandler.sendCommand(callbackCommand);

		int detectedCard = 0;
		if(player.getCardLeft() != Card.EMPTY)
		{
			detectedCard++;
		}

		if(player.getCardRight() != Card.EMPTY)
		{
			detectedCard++;
		}

		CallbackCommand feedbackCallbackCommand = new CallbackCommand(Scope.PLAYER_FEEDBACK, CommandName.CARD, player.getId(), new JsonPrimitive(detectedCard));
		webSocketHandler.sendCommand(feedbackCallbackCommand);
	}

	@Override
	public void chipsDidChange(Player player, int chips)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.CHIP, CommandName.PLAYER_CHIP, player.getId(),
				new JsonPrimitive(chips));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void winProbabilityDidChange(Player player, int value)
	{
		// Handled in WinProbabilityPlayerListener
	}
}
