package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.BoardListener;
import de.lemonpie.beddomischer.model.card.Card;

public class BoardCallbackListener implements BoardListener {

	private WebSocketHandler webSocketHandler;

	public BoardCallbackListener(WebSocketHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void cardDidChangeAtIndex(int index, Card card) {
		CallbackCommand callbackCommand = new CallbackCommand("board", "card", index,
				new JsonPrimitive(card.getName()));
		webSocketHandler.sendCommand(callbackCommand);
	}
}
