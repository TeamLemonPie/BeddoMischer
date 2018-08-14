package de.lemonpie.beddomischer.network.admin.command.read.player;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandData;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.model.player.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class PlayerOpReadCommandTest
{

	@Test
	public void playerOpAddCommandShouldAddPlayer()
	{
		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_OP, 0, new JsonPrimitive("add"));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(BeddoMischerMain.getPlayers().getData()).hasSize(1);
	}

	@Test
	public void playerOpRemoveCommandShouldRemovePlayer()
	{
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_OP, player.getId(), new JsonPrimitive("remove"));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(BeddoMischerMain.getPlayers().getData()).isEmpty();
	}

	@Test
	public void wrongPlayerOpRemoveCommandShouldRemovePlayer()
	{
		BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_OP, 2, new JsonPrimitive("remove"));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(BeddoMischerMain.getPlayers().getData()).hasSize(1);
	}

}
