package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.CountdownListener;

public class WebSocketCountdownListener implements CountdownListener {

	private WebSocketHandler webSocketHandler;

	public WebSocketCountdownListener(WebSocketHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void countdownDidChange(long value) {
		CallbackCommand callbackCommand = new CallbackCommand(Scope.COUNTDOWN, CommandName.COUNTDOWN, -1, new JsonPrimitive(value));
		webSocketHandler.sendCommand(callbackCommand);
	}
}
