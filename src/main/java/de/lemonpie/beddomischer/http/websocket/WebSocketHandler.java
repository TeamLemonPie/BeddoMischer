package de.lemonpie.beddomischer.http.websocket;

import com.google.gson.Gson;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.tobias.logger.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@WebSocket
public class WebSocketHandler
{

	private Gson gson = new Gson();

	// Store sessions if you want to, for example, broadcast a message to all users
	private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

	@OnWebSocketConnect
	public void connected(Session session)
	{
		sessions.add(session);
	}

	@OnWebSocketClose
	public void closed(Session session, int statusCode, String reason)
	{
		sessions.remove(session);
	}

	@OnWebSocketMessage
	public void onRead(Session session, String message) throws IOException
	{
		Logger.debug("Read from WebSocket: {0}", message);
		CommandData commandData = gson.fromJson(message, CommandData.class);

		CommandExecutor.getInstance().execute(commandData);
	}

	public void sendCommand(CallbackCommand command)
	{
		String json = gson.toJson(command);
		sessions.forEach(session -> {
			try
			{
				session.getRemote().sendString(json);
			}
			catch(IOException e)
			{
				Logger.error(e);
			}
		});
	}
}
