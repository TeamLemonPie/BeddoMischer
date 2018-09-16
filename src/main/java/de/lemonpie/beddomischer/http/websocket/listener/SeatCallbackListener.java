package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddocommon.model.seat.SeatListener;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;

public class SeatCallbackListener implements SeatListener
{
	private WebSocketHandler webSocket;

	public SeatCallbackListener(WebSocketHandler webSocket)
	{
		this.webSocket = webSocket;
	}

	@Override
	public void readerIdDidChange(Seat seat, int readerId)
	{
	}

	@Override
	public void playerIdDidChange(Seat seat, int playerId)
	{
		webSocket.sendCommand(new CallbackCommand(Scope.PLAYER_FEEDBACK, CommandName.SEAT, seat.getId(), new JsonPrimitive(playerId)));
		webSocket.sendCommand(new CallbackCommand(Scope.PLAYER, CommandName.SEAT, seat.getId(), new JsonPrimitive(playerId)));
	}
}
