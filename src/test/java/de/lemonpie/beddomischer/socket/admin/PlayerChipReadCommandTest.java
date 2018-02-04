package de.lemonpie.beddomischer.socket.admin;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.socket.CommandData;
import de.lemonpie.beddomischer.socket.admin.command.read.player.PlayerChipsReadCommand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BeddoMischerTestRunner.class)
public class PlayerChipReadCommandTest {

	@Test
	public void playerNameCommandShouldReturnPlayerName() {
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.PLAYER_CHIP, player.getId(), new JsonPrimitive(10000));
		PlayerChipsReadCommand command = new PlayerChipsReadCommand();
		command.execute(commandData);

		Assert.assertEquals(10000, player.getChips());
	}

}
