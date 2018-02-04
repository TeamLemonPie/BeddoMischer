package de.lemonpie.beddomischer.socket.admin;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.socket.CommandData;
import de.lemonpie.beddomischer.socket.admin.command.read.player.PlayerOpReadCommand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BeddoMischerTestRunner.class)
public class PlayerOpReadCommandTest {

	@Test
	public void playerOpAddCommandShouldAddPlayer() {
		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_OP, 0, new JsonPrimitive("add"));
		PlayerOpReadCommand command = new PlayerOpReadCommand();
		command.execute(commandData);

		Assert.assertEquals(1, BeddoMischerMain.getPlayers().getData().size());
	}

	@Test
	public void playerOpRemoveCommandShouldRemovePlayer() {
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_OP, player.getId(), new JsonPrimitive("remove"));
		PlayerOpReadCommand command = new PlayerOpReadCommand();
		command.execute(commandData);

		Assert.assertEquals(0, BeddoMischerMain.getPlayers().getData().size());
	}

	@Test
	public void wrongPlayerOpRemoveCommandShouldRemovePlayer() {
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_OP, 2, new JsonPrimitive("remove"));
		PlayerOpReadCommand command = new PlayerOpReadCommand();
		command.execute(commandData);

		Assert.assertEquals(1, BeddoMischerMain.getPlayers().getData().size());
	}

}
