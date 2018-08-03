package de.lemonpie.beddomischer.socket.admin.command.read.player;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerState;
import de.lemonpie.beddomischer.socket.CommandData;
import de.lemonpie.beddomischer.socket.CommandExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class PlayerStateReadCommandTest
{

	@Test
	public void playerStateCommandShouldReturnState()
	{
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_STATE, player.getId(), new JsonPrimitive(PlayerState.OUT_OF_GAME.name()));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(player.getPlayerState()).isEqualTo(PlayerState.OUT_OF_GAME);
	}

}
