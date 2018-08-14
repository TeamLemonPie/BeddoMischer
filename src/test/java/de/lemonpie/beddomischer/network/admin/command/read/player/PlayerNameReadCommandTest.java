package de.lemonpie.beddomischer.network.admin.command.read.player;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.model.player.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class PlayerNameReadCommandTest
{

	@Test
	public void playerNameCommandShouldReturnPlayerName()
	{
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_NAME, player.getId(), new JsonPrimitive("Kevin"));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(player.getName()).isEqualTo("Kevin");
	}

}
