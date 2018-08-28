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
public class PlayerHighlightReadCommandTest
{

	@Test
	public void playerHighlightCommandShouldReturnNormal()
	{
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_HIGHLIGHT, player.getId(), new JsonPrimitive(true));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(player.isHighlighted()).isTrue();
	}

	@Test
	public void playerDehighlightCommandShouldReturnNormal()
	{
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_HIGHLIGHT, player.getId(), new JsonPrimitive(false));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(player.isHighlighted()).isFalse();
	}

	@Test
	public void twoPlayerHighlightCommandShouldReturnNormal()
	{
		Player player = BeddoMischerMain.getPlayers().add();
		Player player2 = BeddoMischerMain.getPlayers().add();
		player2.setHighlighted(true);

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_HIGHLIGHT, player.getId(), new JsonPrimitive(true));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(player.isHighlighted()).isTrue();
		assertThat(player2.isHighlighted()).isFalse();
	}

}
