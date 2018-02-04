package de.lemonpie.beddomischer.socket.reader;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.Scope;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.card.CardSymbol;
import de.lemonpie.beddomischer.model.card.CardValue;
import de.lemonpie.beddomischer.socket.CommandData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BeddoMischerTestRunner.class)
public class CardReadCommandTest {

	@Test
	public void oneCardShouldReturnNormal() {
		Player player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(1);

		CommandData commandData = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-2"));
		CardReadCommand cardReadCommand = new CardReadCommand();
		cardReadCommand.execute(commandData);

		Assert.assertEquals(new Card(CardSymbol.CROSS, CardValue.TWO), player.getCardLeft());
	}

	@Test
	public void twoCardsShouldReturnNormal() {
		Player player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(1);

		CommandData commandData = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-2"));
		CommandData commandData2 = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-3"));
		CardReadCommand cardReadCommand = new CardReadCommand();
		cardReadCommand.execute(commandData);
		cardReadCommand.execute(commandData2);

		Assert.assertEquals(new Card(CardSymbol.CROSS, CardValue.TWO), player.getCardLeft());
		Assert.assertEquals(new Card(CardSymbol.CROSS, CardValue.THREE), player.getCardRight());
	}

	@Test
	public void threeCardsShouldBeIgnored() {
		Player player = BeddoMischerMain.getPlayers().add();
		player.setReaderId(1);

		CommandData commandData = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-2"));
		CommandData commandData2 = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-3"));
		CommandData commandData3 = new CommandData(Scope.READER, CommandName.CARD, 1, new JsonPrimitive("Kr-4"));
		CardReadCommand cardReadCommand = new CardReadCommand();
		cardReadCommand.execute(commandData);
		cardReadCommand.execute(commandData2);
		cardReadCommand.execute(commandData3);

		Assert.assertEquals(new Card(CardSymbol.CROSS, CardValue.TWO), player.getCardLeft());
		Assert.assertEquals(new Card(CardSymbol.CROSS, CardValue.THREE), player.getCardRight());
	}
}
