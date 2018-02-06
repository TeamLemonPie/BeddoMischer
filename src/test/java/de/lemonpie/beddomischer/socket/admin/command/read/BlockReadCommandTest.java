package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.model.BlockOption;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.socket.CommandData;
import de.lemonpie.beddomischer.socket.CommandExecutor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BeddoMischerTestRunner.class)
public class BlockReadCommandTest {

	@Test
	public void blockModeSendShouldReturnNormal() {
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.BLOCK, BlockOption.BOARD.ordinal(), null);
		CommandExecutor.getInstance().execute(commandData);

		Assert.assertEquals(BlockOption.BOARD, BeddoMischerMain.getBlockOption());
	}

}