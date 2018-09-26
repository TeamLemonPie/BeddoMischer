package de.lemonpie.beddomischer.network.admin.command.send;

import com.google.gson.*;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Board;

public class DataSendCommand extends CommandData
{

	public DataSendCommand()
	{
		super(Scope.ADMIN, CommandName.DATA, 0, null);

		Gson gson = new Gson();

		JsonObject data = new JsonObject();
		JsonArray board = new JsonArray();
		JsonArray boardReader = new JsonArray();

		JsonElement seats = gson.toJsonTree(BeddoMischerMain.getSeatList().getData());
		JsonElement players = gson.toJsonTree(BeddoMischerMain.getPlayers().getData());

		// Serialize board
		Board b = BeddoMischerMain.getBoard();
		for(int i = 0; i < b.getCards().length; i++)
		{
			JsonObject obj = new JsonObject();
			obj.addProperty("id", i);
			obj.addProperty("card", b.getCard(i).getName());
			board.add(obj);
		}

		for(int readerId : b.getReaderIds())
		{
			boardReader.add(new JsonPrimitive(readerId));
		}

		data.add("players", players);
		data.add("board", board);
		data.add("board-reader", boardReader);
		data.add("seats", seats);

		data.addProperty("reader-count", BeddoMischerMain.getRfidServerSocket().count());

		setValue(data);
	}
}
