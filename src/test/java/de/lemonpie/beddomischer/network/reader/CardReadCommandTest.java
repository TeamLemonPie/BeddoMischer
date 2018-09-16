package de.lemonpie.beddomischer.network.reader;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.model.card.CardSymbol;
import de.lemonpie.beddocommon.model.card.CardValue;
import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.player.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class CardReadCommandTest
{

	@Test
	public void onePlayerCardShouldReturnNormal()
	{
		BeddoMischerMain.getSeatList().add(new Seat(1, 1, 0));
		Player player = BeddoMischerMain.getPlayers().add();

		CommandData commandData = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-2"));

		CommandExecutor.getInstance().execute(commandData);

		assertThat(new Card(CardSymbol.CROSS, CardValue.TWO)).isEqualTo(player.getCardLeft());
	}

	@Test
	public void twoPlayerCardsShouldReturnNormal()
	{
		BeddoMischerMain.getSeatList().add(new Seat(1, 1, 0));
		Player player = BeddoMischerMain.getPlayers().add();

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
		BeddoMischerMain.getSeatList().add(new Seat(1, 1, 0));
		Player player = BeddoMischerMain.getPlayers().add();

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
		board.addReaderId((byte) 1);

		CommandData commandData = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-2"));
		CommandExecutor.getInstance().execute(commandData);
	}
}
