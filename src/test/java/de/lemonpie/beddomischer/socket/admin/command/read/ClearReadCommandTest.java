package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.model.card.CardSymbol;
import de.lemonpie.beddocommon.model.card.CardValue;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandData;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class ClearReadCommandTest
{

	private Player player;
	private Board board;

	@Before
	public void setUp()
	{
		player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(0);
		player.setCardLeft(new Card(CardSymbol.CROSS, CardValue.ACE));
		player.setCardRight(new Card(CardSymbol.HEART, CardValue.TEN));

		board = BeddoMischerMain.getBoard();
		board.setCard(0, new Card(CardSymbol.CROSS, CardValue.FIVE));
		board.setCard(1, new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
	}

	@Test
	public void clearPlayerCardShouldReturnNormal()
	{
		CommandData data = new CommandData(Scope.ADMIN, CommandName.CLEAR, player.getId(), null);
		CommandExecutor.getInstance().execute(data);

		assertThat(player.getCardLeft()).isEqualTo(Card.EMPTY);
		assertThat(player.getCardRight()).isEqualTo(Card.EMPTY);

		assertThat(new Card(CardSymbol.CROSS, CardValue.FIVE)).isEqualTo(board.getCard(0));
		assertThat(new Card(CardSymbol.DIAMONDS, CardValue.FIVE)).isEqualTo(board.getCard(1));
	}

	@Test
	public void clearBoardCardsShouldReturnNormal()
	{
		CommandData data = new CommandData(Scope.ADMIN, CommandName.CLEAR, -2, null);
		CommandExecutor.getInstance().execute(data);

		assertThat(new Card(CardSymbol.CROSS, CardValue.ACE)).isEqualTo(player.getCardLeft());
		assertThat(new Card(CardSymbol.HEART, CardValue.TEN)).isEqualTo(player.getCardRight());

		assertThat(board.getCard(0)).isEqualTo(Card.EMPTY);
		assertThat(board.getCard(1)).isEqualTo(Card.EMPTY);
	}

	@Test
	public void clearAllCardsShouldReturnNormal()
	{
		CommandData data = new CommandData(Scope.ADMIN, CommandName.CLEAR, -1, null);
		CommandExecutor.getInstance().execute(data);

		assertThat(player.getCardLeft()).isEqualTo(Card.EMPTY);
		assertThat(player.getCardRight()).isEqualTo(Card.EMPTY);

		assertThat(board.getCard(0)).isEqualTo(Card.EMPTY);
		assertThat(board.getCard(1)).isEqualTo(Card.EMPTY);
	}

}
