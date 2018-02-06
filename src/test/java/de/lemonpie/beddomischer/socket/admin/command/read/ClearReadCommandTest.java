package de.lemonpie.beddomischer.socket.admin.command.read;

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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BeddoMischerTestRunner.class)
public class ClearReadCommandTest {

	private Player player;
	private Board board;

	@Before
	public void setUp() {
		player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(0);
		player.setCardLeft(new Card(CardSymbol.CROSS, CardValue.ACE));
		player.setCardRight(new Card(CardSymbol.HEART, CardValue.TEN));

		board = BeddoMischerMain.getBoard();
		board.setCard(0, new Card(CardSymbol.CROSS, CardValue.FIVE));
		board.setCard(1, new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
	}

	@Test
	public void clearPlayerCardShouldReturnNormal() {
		CommandData data = new CommandData(Scope.ADMIN, CommandName.CLEAR, player.getId(), null);
		CommandExecutor.getInstance().execute(data);

		Assert.assertEquals(Card.EMPTY, player.getCardLeft());
		Assert.assertEquals(Card.EMPTY, player.getCardRight());

		Assert.assertEquals(new Card(CardSymbol.CROSS, CardValue.FIVE), board.getCard(0));
		Assert.assertEquals(new Card(CardSymbol.DIAMONDS, CardValue.FIVE), board.getCard(1));
	}

	@Test
	public void clearBoardCardsShouldReturnNormal() {
		CommandData data = new CommandData(Scope.ADMIN, CommandName.CLEAR, -2, null);
		CommandExecutor.getInstance().execute(data);

		Assert.assertEquals(new Card(CardSymbol.CROSS, CardValue.ACE), player.getCardLeft());
		Assert.assertEquals(new Card(CardSymbol.HEART, CardValue.TEN), player.getCardRight());

		Assert.assertEquals(Card.EMPTY, board.getCard(0));
		Assert.assertEquals(Card.EMPTY, board.getCard(1));
	}

	@Test
	public void clearAllCardsShouldReturnNormal() {
		CommandData data = new CommandData(Scope.ADMIN, CommandName.CLEAR, -1, null);
		CommandExecutor.getInstance().execute(data);

		Assert.assertEquals(Card.EMPTY, player.getCardLeft());
		Assert.assertEquals(Card.EMPTY, player.getCardRight());

		Assert.assertEquals(Card.EMPTY, board.getCard(0));
		Assert.assertEquals(Card.EMPTY, board.getCard(1));
	}

}
