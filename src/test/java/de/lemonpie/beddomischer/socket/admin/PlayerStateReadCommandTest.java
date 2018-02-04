package de.lemonpie.beddomischer.socket.admin;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerState;
import de.lemonpie.beddomischer.socket.CommandData;
import de.lemonpie.beddomischer.socket.admin.command.read.player.PlayerStateReadCommand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BeddoMischerTestRunner.class)
public class PlayerStateReadCommandTest {

	@Test
	public void playerStateCommandShouldReturnState() {
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_STATE, player.getId(), new JsonPrimitive(PlayerState.OUT_OF_GAME.name()));
		PlayerStateReadCommand command = new PlayerStateReadCommand();
		command.execute(commandData);

		Assert.assertEquals(PlayerState.OUT_OF_GAME, player.getPlayerState());
	}

}
