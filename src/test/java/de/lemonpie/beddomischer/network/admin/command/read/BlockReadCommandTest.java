package de.lemonpie.beddomischer.network.admin.command.read;

import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.model.BlockOption;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class BlockReadCommandTest
{

	@Test
	public void blockModeSendShouldReturnNormal()
	{
		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.BLOCK, BlockOption.BOARD.ordinal(), null);
		CommandExecutor.getInstance().execute(commandData);

		assertThat(BeddoMischerMain.getBlockOption()).isEqualTo(BlockOption.BOARD);
	}

}
