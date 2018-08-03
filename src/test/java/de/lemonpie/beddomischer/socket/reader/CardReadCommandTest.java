package de.lemonpie.beddomischer.socket.reader;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.card.CardSymbol;
import de.lemonpie.beddomischer.model.card.CardValue;
import de.lemonpie.beddomischer.socket.CommandData;
import de.lemonpie.beddomischer.socket.CommandExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class CardReadCommandTest
{

	@Test
	public void onePlayerCardShouldReturnNormal()
	{
		Player player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(1);

		CommandData commandData = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-2"));

		CommandExecutor.getInstance().execute(commandData);

		assertThat(new Card(CardSymbol.CROSS, CardValue.TWO)).isEqualTo(player.getCardLeft());
	}

	@Test
	public void twoPlayerCardsShouldReturnNormal()
	{
		Player player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(1);

		CommandData commandData = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-2"));
		CommandData commandData2 = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-3"));

		CommandExecutor.getInstance().execute(commandData);
		CommandExecutor.getInstance().execute(commandData2);

		assertThat(new Card(CardSymbol.CROSS, CardValue.TWO)).isEqualTo(player.getCardLeft());
		assertThat(new Card(CardSymbol.CROSS, CardValue.THREE)).isEqualTo(player.getCardRight());
	}

	@Test
	public void threePlayerCardsShouldBeIgnored()
	{
		Player player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(1);

		CommandData commandData = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-2"));
		CommandData commandData2 = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-3"));
		CommandData commandData3 = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-4"));

		CommandExecutor.getInstance().execute(commandData);
		CommandExecutor.getInstance().execute(commandData2);
		CommandExecutor.getInstance().execute(commandData3);

		assertThat(new Card(CardSymbol.CROSS, CardValue.TWO)).isEqualTo(player.getCardLeft());
		assertThat(new Card(CardSymbol.CROSS, CardValue.THREE)).isEqualTo(player.getCardRight());
	}

	@Test
	public void oneBoardCardShouldReturnNormal()
	{
		Board board = BeddoMischerMain.getBoard();
		board.addReaderId(1);

		CommandData commandData = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-2"));
		CommandExecutor.getInstance().execute(commandData);
	}
}
