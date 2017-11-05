package de.lemonpie.beddomischer.model.winprobability;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.BlockOption;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.card.CardSymbol;
import de.lemonpie.beddomischer.model.card.CardValue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestWinProbability
{
	@Test
	public void test()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);
		ArrayList<Player> players = new ArrayList<>();
		
		Player player1 = new Player(0);
		player1.setName("1");
		player1.setCardLeft(new Card(CardSymbol.SPADES, CardValue.QUEEN));
		player1.setCardRight(new Card(CardSymbol.CROSS, CardValue.EIGHT));
		players.add(player1);

		Player player2 = new Player(1);
		player2.setName("2");
		player2.setCardLeft(new Card(CardSymbol.HEART, CardValue.THREE));
		player2.setCardRight(new Card(CardSymbol.HEART, CardValue.TWO));
		players.add(player2);
		
		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
		board.setCard(1, new Card(CardSymbol.DIAMONDS, CardValue.QUEEN));
		board.setCard(2, new Card(CardSymbol.HEART, CardValue.QUEEN));
		board.setCard(3, new Card(CardSymbol.DIAMONDS, CardValue.SIX));
		
		Calculation calculation = new Calculation(players, board);
        List<Double> probabilities = calculation.calculate(1);
        ArrayList<Integer> percentages = new ArrayList<>();
		
		for(int i = 0; i < players.size(); i++)
		{
			percentages.add((int)(probabilities.get(i) * 100));
		}
		
		assertEquals(Integer.valueOf(100), percentages.get(0));
		assertEquals(Integer.valueOf(0), percentages.get(1));
	}
	
	@Test
	public void test2()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);
		ArrayList<Player> players = new ArrayList<>();
		
		Player player1 = new Player(0);
		player1.setName("1");
		player1.setCardLeft(new Card(CardSymbol.SPADES, CardValue.QUEEN));
		player1.setCardRight(new Card(CardSymbol.CROSS, CardValue.EIGHT));
		players.add(player1);

		Player player2 = new Player(1);
		player2.setName("2");
		player2.setCardLeft(new Card(CardSymbol.HEART, CardValue.THREE));
		player2.setCardRight(new Card(CardSymbol.HEART, CardValue.TWO));
		players.add(player2);
		
		Player player3 = new Player(2);
		player3.setName("3");
		player3.setCardLeft(new Card(CardSymbol.DIAMONDS, CardValue.EIGHT));
		player3.setCardRight(new Card(CardSymbol.HEART, CardValue.EIGHT));
		players.add(player3);
		
		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
		board.setCard(1, new Card(CardSymbol.DIAMONDS, CardValue.QUEEN));
		board.setCard(2, new Card(CardSymbol.HEART, CardValue.QUEEN));
		board.setCard(3, new Card(CardSymbol.DIAMONDS, CardValue.SIX));
		
		Calculation calculation = new Calculation(players, board);
        List<Double> probabilities = calculation.calculate(10000);
        ArrayList<Integer> percentages = new ArrayList<>();
		
		for(int i = 0; i < players.size(); i++)
		{
			percentages.add((int)(probabilities.get(i) * 100));
		}

		assertTrue(62 < percentages.get(0) && percentages.get(0) < 67);
		assertTrue(12 < percentages.get(1) && percentages.get(1) < 16);
		assertTrue(18 < percentages.get(2) && percentages.get(2) < 23);
	}

	@Test
	public void test3()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);
		ArrayList<Player> players = new ArrayList<>();

		Player player1 = new Player(0);
		player1.setName("1");
		player1.setCardLeft(new Card(CardSymbol.SPADES, CardValue.QUEEN));
		player1.setCardRight(new Card(CardSymbol.CROSS, CardValue.EIGHT));
		players.add(player1);

		Player player2 = new Player(1);
		player2.setName("2");
		player2.setCardLeft(new Card(CardSymbol.HEART, CardValue.THREE));
		player2.setCardRight(new Card(CardSymbol.HEART, CardValue.TWO));
		players.add(player2);

		Board board = new Board();

		Calculation calculation = new Calculation(players, board);
		List<Double> probabilities = calculation.calculate(2);
		ArrayList<Integer> percentages = new ArrayList<>();

		for(int i = 0; i < players.size(); i++)
		{
			percentages.add((int)(probabilities.get(i) * 100));
		}

		assertTrue(percentages.get(0) + percentages.get(1) <= 100);
	}
}