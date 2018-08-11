package de.lemonpie.beddomischer.socket.admin.command.send;

import com.google.gson.JsonObject;
import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandData;

/**
 * Forward cards to admin control.
 */
public class CardSendCommand extends CommandData
{

	public CardSendCommand(int boardId, Card card)
	{
		super(Scope.ADMIN, CommandName.CARD, boardId, null);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 1);
		jsonObject.addProperty("card", card.getName());
		setValue(jsonObject);
	}

	public CardSendCommand(int playerId, int index, Card card)
	{
		super(Scope.ADMIN, CommandName.CARD, playerId, null);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 0);
		jsonObject.addProperty("index", index);
		jsonObject.addProperty("card", card.getName());
		setValue(jsonObject);
	}
}
