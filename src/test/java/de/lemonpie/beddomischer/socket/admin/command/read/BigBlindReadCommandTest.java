package de.lemonpie.beddomischer.socket.admin.command.read;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandData;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class BigBlindReadCommandTest
{

	@Test
	public void bigBlindSendShouldReturnNormal()
	{
		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.BIG_BLIND, 0, new JsonPrimitive(10000));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(BeddoMischerMain.getBoard().getBigBlind()).isEqualTo(10000);
	}

}
