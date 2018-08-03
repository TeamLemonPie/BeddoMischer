package de.lemonpie.beddomischer.socket.admin.command.send;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.socket.CommandData;

public class DataSendCommand extends CommandData
{

	public DataSendCommand()
	{
		super(Scope.ADMIN, CommandName.DATA, 0, null);

		JsonObject data = new JsonObject();
		JsonArray players = new JsonArray();
		JsonArray board = new JsonArray();
		JsonArray boardReader = new JsonArray();

		// Serialize player
		BeddoMischerMain.getPlayers().forEach(player -> {
			JsonObject obj = new JsonObject();

			obj.addProperty("id", player.getId());
			obj.addProperty("name", player.getName());
			obj.addProperty("twitchName", player.getTwitchName());
			obj.addProperty("state", player.getPlayerState().name());

			obj.addProperty("chips", player.getChips());

			obj.addProperty("cardLeft", player.getCardLeft().getName());
			obj.addProperty("cardRight", player.getCardRight().getName());

			obj.addProperty("readerId", player.getReaderId());

			players.add(obj);
		});

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

		data.addProperty("reader-count", BeddoMischerMain.getRfidServerSocket().count());

		setValue(data);
	}
}
