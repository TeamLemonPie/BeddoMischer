package de.lemonpie.beddomischer.http.websocket.listener;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddomischer.http.websocket.CallbackCommand;
import de.lemonpie.beddomischer.http.websocket.WebSocketHandler;
import de.lemonpie.beddomischer.listener.OverlayListener;
import de.lemonpie.beddomischer.model.Overlay;

public class OverlayCallbackListener implements OverlayListener
{
	private WebSocketHandler websocket;

	public OverlayCallbackListener(WebSocketHandler websocket)
	{
		this.websocket = websocket;
	}

	@Override
	public void hidePlayerDidChange(Overlay overlay)
	{
		final CallbackCommand callbackBoard = new CallbackCommand(Scope.PLAYER, CommandName.OVERLAY_HIDE, 0, new JsonPrimitive(overlay.isHidePlayer()));
		websocket.sendCommand(callbackBoard);
	}

	@Override
	public void hideBoardDidChange(Overlay overlay)
	{
		final CallbackCommand callbackBoard = new CallbackCommand(Scope.BOARD, CommandName.OVERLAY_HIDE, 0, new JsonPrimitive(overlay.isHideBoard()));
		websocket.sendCommand(callbackBoard);
	}
}
