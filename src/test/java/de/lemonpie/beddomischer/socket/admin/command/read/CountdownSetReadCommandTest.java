package de.lemonpie.beddomischer.socket.admin.command.read;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.socket.CommandData;
import de.lemonpie.beddomischer.socket.CommandExecutor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BeddoMischerTestRunner.class)
public class CountdownSetReadCommandTest {

	@Test
	public void countdownForPauseEndSetShouldReturnNormal() {
		int minutes = 10;
		long time = System.currentTimeMillis() + 1000 * 60 * minutes;

		CommandData data = new CommandData(Scope.ADMIN, CommandName.PAUSE, 0, new JsonPrimitive(minutes));
		CommandExecutor.getInstance().execute(data);

		Assert.assertEquals(time, BeddoMischerMain.getPauseEndTime());
	}

	@Test
	public void countdownForPauseStartSetShouldReturnNormal() {
		int minutes = 5;
		long time = System.currentTimeMillis() + 1000 * 60 * minutes;

		CommandData data = new CommandData(Scope.ADMIN, CommandName.PAUSE, 1, new JsonPrimitive(minutes));
		CommandExecutor.getInstance().execute(data);

		Assert.assertEquals(time, BeddoMischerMain.getPauseStartTime());
	}
}
