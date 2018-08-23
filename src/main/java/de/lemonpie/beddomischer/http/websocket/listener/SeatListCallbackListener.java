package de.lemonpie.beddomischer.http.websocket.listener;

import de.lemonpie.beddocommon.model.ObservableListListener;
import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;

public class SeatListCallbackListener implements ObservableListListener<Seat>
{
	private WebSocketHandler webSocketHandler;

	public SeatListCallbackListener(WebSocketHandler webSocketHandler)
	{
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void addObjectToList(Seat obj)
	{
		obj.addListener(new SeatCallbackListener(webSocketHandler));
	}

	@Override
	public void removeObjectFromList(Seat obj)
	{

	}
}
