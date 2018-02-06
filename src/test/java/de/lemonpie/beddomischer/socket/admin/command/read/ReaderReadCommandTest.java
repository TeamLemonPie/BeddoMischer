package de.lemonpie.beddomischer.socket.admin.command.read;

import com.google.gson.JsonObject;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.socket.CommandData;
import de.lemonpie.beddomischer.socket.CommandExecutor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BeddoMischerTestRunner.class)
public class ReaderReadCommandTest {

	@Test
	public void setOnePlayerReaderShouldReturnNormal() {
		Player player = BeddoMischerMain.getPlayers().add();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 0); // Player Type
		jsonObject.addProperty("playerId", player.getId());
		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		Assert.assertEquals(3, player.getReaderId());
	}

	@Test
	public void setOnePlayerReaderWithPreviousPlayerReaderShouldReturnNormal() {
		Player player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(2);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 0); // Player Type
		jsonObject.addProperty("playerId", player.getId());
		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		Assert.assertEquals(3, player.getReaderId());
	}

	@Test
	public void setOnePlayerReaderWithPreviousBoardReaderShouldReturnNormal() {
		Player player = BeddoMischerMain.getPlayers().add();
		BeddoMischerMain.getBoard().addReaderId(3);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 0); // Player Type
		jsonObject.addProperty("playerId", player.getId());
		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		Assert.assertFalse(BeddoMischerMain.getBoard().getReaderIds().contains(3));
		Assert.assertEquals(3, player.getReaderId());
	}

	@Test
	public void setOneBoardReaderShouldReturnNormal() {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 1); // Board Type
		jsonObject.addProperty("oldReaderId", -3);

		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		Assert.assertTrue(BeddoMischerMain.getBoard().getReaderIds().contains(3));
	}

	@Test
	public void setOneBoardReaderWithPreviousPlayerReaderShouldReturnNormal() {
		Player player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(3);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 1); // Board Type
		jsonObject.addProperty("oldReaderId", -3);

		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		Assert.assertEquals(-3, player.getReaderId());
		Assert.assertTrue(BeddoMischerMain.getBoard().getReaderIds().contains(3));
	}
}
