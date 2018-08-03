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
public class SmallBlindReadCommandTest
{

	@Test
	public void smallBlindSendShouldReturnNormal()
	{
		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.SMALL_BLIND, 0, new JsonPrimitive(10000));
		CommandExecutor.getInstance().execute(commandData);

		Assert.assertEquals(10000, BeddoMischerMain.getBoard().getSmallBlind());
	}

}