package de.lemonpie.beddomischer.network.admin.command.read;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.model.card.CardSymbol;
import de.lemonpie.beddocommon.model.card.CardValue;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class BoardCardReadCommandTest
{

	@Test
	public void oneBoardCardSetShouldReturnNormal()
	{
		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.BOARD_CARD, 0, new JsonPrimitive("Kr-3"));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(new Card(CardSymbol.CROSS, CardValue.THREE)).isEqualTo(BeddoMischerMain.getBoard().getCard(0));
	}

	@Test
	public void sameBoardCardSetShouldFail()
	{
		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.BOARD_CARD, 0, new JsonPrimitive("Kr-3"));
		CommandData commandData2 = new CommandData(Scope.ADMIN, CommandName.BOARD_CARD, 1, new JsonPrimitive("Kr-3"));

		CommandExecutor.getInstance().execute(commandData);
		CommandExecutor.getInstance().execute(commandData2);

		assertThat(new Card(CardSymbol.CROSS, CardValue.THREE)).isEqualTo(BeddoMischerMain.getBoard().getCard(0));
		assertThat(BeddoMischerMain.getBoard().getCard(1)).isEqualTo(Card.EMPTY);
	}

}
