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
		List<Double> probabilities = calculation.calculate(2000);
		ArrayList<Integer> percentages = new ArrayList<>();

		for(int i = 0; i < players.size(); i++)
		{
			percentages.add((int) (probabilities.get(i) * 100));
		}

		assertTrue(percentages.get(0) > 89);
		assertTrue(percentages.get(1) < 10);
	}

	@Test
	public void testUnequalPairs()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);
		ArrayList<Player> players = new ArrayList<>();

		// player 1 has the highest pair (pair of SIX)
		Player player1 = new Player(0);
		player1.setName("1");
		player1.setCardLeft(new Card(CardSymbol.HEART, CardValue.SIX));
		player1.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.FIVE));

		// player 2 has a pair of TWO but his highest card is a KING
		Player player2 = new Player(1);
		player2.setName("2");
		player2.setCardLeft(new Card(CardSymbol.SPADES, CardValue.KING));
		player2.setCardRight(new Card(CardSymbol.SPADES, CardValue.TWO));

		players.add(player1);
		players.add(player2);

		Board board = new Board();
		board.setCard(new Card(CardSymbol.DIAMONDS, CardValue.EIGHT));
		board.setCard(new Card(CardSymbol.SPADES, CardValue.QUEEN));
		board.setCard(new Card(CardSymbol.SPADES, CardValue.SIX));
		board.setCard(new Card(CardSymbol.DIAMONDS, CardValue.THREE));
		board.setCard(new Card(CardSymbol.DIAMONDS, CardValue.TWO));

		Calculation calculation = new Calculation(players, board);
		List<Double> probabilities = calculation.calculate(10);
		ArrayList<Integer> percentages = new ArrayList<>();

		for(int i = 0; i < players.size(); i++)
		{
			percentages.add((int) (probabilities.get(i) * 100));
		}

		assertTrue(percentages.get(0) == 100);
		assertTrue(percentages.get(1) == 0);
	}

	@Test
	public void testEqualPairs()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);
		ArrayList<Player> players = new ArrayList<>();

		// player 1 has the pair of SIX)
		Player player1 = new Player(0);
		player1.setName("1");
		player1.setCardLeft(new Card(CardSymbol.HEART, CardValue.SIX));
		player1.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.FIVE));

		// player 2 has also a pair of six a KING as highest card
		Player player2 = new Player(1);
		player2.setName("2");
		player2.setCardLeft(new Card(CardSymbol.SPADES, CardValue.KING));
		player2.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.SIX));

		players.add(player1);
		players.add(player2);

		Board board = new Board();
		board.setCard(new Card(CardSymbol.DIAMONDS, CardValue.EIGHT));
		board.setCard(new Card(CardSymbol.SPADES, CardValue.QUEEN));
		board.setCard(new Card(CardSymbol.SPADES, CardValue.SIX));
		board.setCard(new Card(CardSymbol.DIAMONDS, CardValue.THREE));
		board.setCard(new Card(CardSymbol.DIAMONDS, CardValue.TWO));

		Calculation calculation = new Calculation(players, board);
		List<Double> probabilities = calculation.calculate(10);
		ArrayList<Integer> percentages = new ArrayList<>();

		for(int i = 0; i < players.size(); i++)
		{
			percentages.add((int) (probabilities.get(i) * 100));
		}

		assertTrue(percentages.get(0) == 0);
		assertTrue(percentages.get(1) == 100);
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
			percentages.add((int) (probabilities.get(i) * 100));
		}

		assertTrue(67 < percentages.get(0) && percentages.get(0) < 75);
		assertTrue(3 < percentages.get(1) && percentages.get(1) < 10);
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
			percentages.add((int) (probabilities.get(i) * 100));
		}

		assertTrue(percentages.get(0) + percentages.get(1) <= 100);
	}

	@Test
	public void test4()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);
		ArrayList<Player> players = new ArrayList<>();

		Player player1 = new Player(0);
		player1.setName("1");
		player1.setCardLeft(new Card(CardSymbol.HEART, CardValue.TWO));
		player1.setCardRight(new Card(CardSymbol.HEART, CardValue.THREE));
		players.add(player1);

		Player player2 = new Player(1);
		player2.setName("2");
		player2.setCardLeft(new Card(CardSymbol.CROSS, CardValue.TWO));
		player2.setCardRight(new Card(CardSymbol.CROSS, CardValue.ACE));
		players.add(player2);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.FOUR));
		board.setCard(1, new Card(CardSymbol.SPADES, CardValue.FIVE));
		board.setCard(2, new Card(CardSymbol.SPADES, CardValue.SIX));
		board.setCard(3, new Card(CardSymbol.SPADES, CardValue.SEVEN));
		board.setCard(4, new Card(CardSymbol.SPADES, CardValue.TEN));

		Calculation calculation = new Calculation(players, board);
		List<Double> probabilities = calculation.calculate(1);
		ArrayList<Integer> percentages = new ArrayList<>();

		for(int i = 0; i < players.size(); i++)
		{
			percentages.add((int) (probabilities.get(i) * 100));
		}

		assertTrue(percentages.get(0) == 100);
		assertTrue(percentages.get(1) == 0);
	}

	@Test
	public void test5()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);
		ArrayList<Player> players = new ArrayList<>();

		Player player1 = new Player(0);
		player1.setName("1");
		player1.setCardLeft(new Card(CardSymbol.SPADES, CardValue.QUEEN));
		player1.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.EIGHT));
		players.add(player1);

		Player player2 = new Player(1);
		player2.setName("2");
		player2.setCardLeft(new Card(CardSymbol.HEART, CardValue.NINE));
		player2.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.SIX));
		players.add(player2);

		Player player3 = new Player(2);
		player3.setName("3");
		player3.setCardLeft(new Card(CardSymbol.HEART, CardValue.TEN));
		player3.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.NINE));
		players.add(player3);

		Player player4 = new Player(3);
		player4.setName("4");
		player4.setCardLeft(new Card(CardSymbol.DIAMONDS, CardValue.QUEEN));
		player4.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
		players.add(player4);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.DIAMONDS, CardValue.SEVEN));
		board.setCard(1, new Card(CardSymbol.CROSS, CardValue.QUEEN));
		board.setCard(2, new Card(CardSymbol.SPADES, CardValue.NINE));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.FIVE));

		Calculation calculation = new Calculation(players, board);
		List<Double> probabilities = calculation.calculate(10000);
		ArrayList<Integer> percentages = new ArrayList<>();

		for(int i = 0; i < players.size(); i++)
		{
			percentages.add((int) (probabilities.get(i) * 100));
		}

		assertTrue(10 < percentages.get(0) && percentages.get(0) < 17);
		assertTrue(4 < percentages.get(1) && percentages.get(1) < 8);
		assertTrue(1 < percentages.get(2) && percentages.get(2) < 5);
		assertTrue(73 < percentages.get(3) && percentages.get(3) < 80);
	}

	@Test
	public void testPreFlop()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);
		ArrayList<Player> players = new ArrayList<>();

		Player player1 = new Player(0);
		player1.setName("1");
		player1.setCardLeft(new Card(CardSymbol.SPADES, CardValue.QUEEN));
		player1.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.EIGHT));
		players.add(player1);

		Player player2 = new Player(1);
		player2.setName("2");
		player2.setCardLeft(new Card(CardSymbol.HEART, CardValue.NINE));
		player2.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.SIX));
		players.add(player2);

		Player player3 = new Player(2);
		player3.setName("3");
		player3.setCardLeft(new Card(CardSymbol.HEART, CardValue.TEN));
		player3.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.NINE));
		players.add(player3);

		Player player4 = new Player(3);
		player4.setName("4");
		player4.setCardLeft(new Card(CardSymbol.DIAMONDS, CardValue.QUEEN));
		player4.setCardRight(new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
		players.add(player4);

		Board board = new Board();

		Calculation calculation = new Calculation(players, board);
		List<Double> probabilities = calculation.calculate(10000);
		ArrayList<Integer> percentages = new ArrayList<>();

		for(int i = 0; i < players.size(); i++)
		{
			percentages.add((int) (probabilities.get(i) * 100));
		}

		assertTrue(20 < percentages.get(0) && percentages.get(0) < 40);
		assertTrue(6 < percentages.get(1) && percentages.get(1) < 26);
		assertTrue(22 < percentages.get(2) && percentages.get(2) < 42);
		assertTrue(10 < percentages.get(3) && percentages.get(3) < 30);
	}
}