package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.CountdownListener;

public class WebSocketCountdownListener implements CountdownListener
{

	private WebSocketHandler webSocketHandler;

	public WebSocketCountdownListener(WebSocketHandler webSocketHandler)
	{
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void pauseCountdownDidChange(long value)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.COUNTDOWN, CommandName.PAUSE, -1, new JsonPrimitive(value));
		CallbackCommand callbackCommandFeedback = new CallbackCommand(Scope.PLAYER_FEEDBACK, CommandName.PAUSE, -1, new JsonPrimitive(value));
		webSocketHandler.sendCommand(callbackCommand);
		webSocketHandler.sendCommand(callbackCommandFeedback);
	}

	@Override
	public void gameCountdownDidChange(long value)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.PLAYER_FEEDBACK, CommandName.NEXT_PAUSE, -1, new JsonPrimitive(value));
		webSocketHandler.sendCommand(callbackCommand);
	}
}
