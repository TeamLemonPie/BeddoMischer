package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.BoardListener;

public class BoardCallbackListener implements BoardListener
{

	private WebSocketHandler webSocketHandler;

	public BoardCallbackListener(WebSocketHandler webSocketHandler)
	{
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void cardDidChangeAtIndex(int index, Card card)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.BOARD, CommandName.CARD, index,
				new JsonPrimitive(card.getName()));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void readerListDidChange()
	{
	}

	@Override
	public void smallBlindDidChange(int newValue)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.BOARD, CommandName.SMALL_BLIND, -1,
				new JsonPrimitive(newValue));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void bigBlindDidChange(int newValue)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.BOARD, CommandName.BIG_BLIND, -1,
				new JsonPrimitive(newValue));
		webSocketHandler.sendCommand(callbackCommand);
	}

	@Override
	public void anteDidChange(int newValue)
	{
		CallbackCommand callbackCommand = new CallbackCommand(Scope.BOARD, CommandName.ANTE, -1,
				new JsonPrimitive(newValue));
		webSocketHandler.sendCommand(callbackCommand);
	}
}
