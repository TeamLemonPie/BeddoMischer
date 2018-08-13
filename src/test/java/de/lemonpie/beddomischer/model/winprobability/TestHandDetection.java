package de.lemonpie.beddomischer.model.winprobability;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.model.card.CardSymbol;
import de.lemonpie.beddocommon.model.card.CardValue;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.BlockOption;
import de.lemonpie.beddomischer.model.Board;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHandDetection
{
	@Test
	public void testRoyalFlush()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.HEART, CardValue.ACE);
		Card cardRight = new Card(CardSymbol.HEART, CardValue.QUEEN);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.SEVEN));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.KING));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.KING));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.JACK));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.TEN));

		Hand hand = new Hand(board, cardLeft, cardRight);
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.ROYAL_FLUSH, calculatedHand.getHandType());
	}

	@Test
	public void testStraightFlush()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.HEART, CardValue.NINE);
		Card cardRight = new Card(CardSymbol.HEART, CardValue.QUEEN);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.SEVEN));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.KING));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.KING));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.JACK));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.TEN));

		Hand hand = new Hand(board, cardLeft, cardRight);
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.STRAIGHT_FLUSH, calculatedHand.getHandType());
	}

	@Test
	public void testFourOfAKind()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.DIAMONDS, CardValue.KING);
		Card cardRight = new Card(CardSymbol.SPADES, CardValue.KING);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.SEVEN));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.KING));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.KING));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.JACK));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.TEN));

		Hand hand = new Hand(board, cardLeft, cardRight);
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.FOUR_OF_A_KIND, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.BACK, CardValue.KING), calculatedHand.getHighestCard().get(0));
	}

	@Test
	public void testFullHouse()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.DIAMONDS, CardValue.KING);
		Card cardRight = new Card(CardSymbol.SPADES, CardValue.KING);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.SEVEN));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.THREE));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.KING));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.SEVEN));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.TEN));

		Hand hand = new Hand(board, cardLeft, cardRight);
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.FULL_HOUSE, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.BACK, CardValue.KING).getValue(), calculatedHand.getHighestCard().get(0).getValue());
		assertEquals(new Card(CardSymbol.BACK, CardValue.SEVEN).getValue(), calculatedHand.getHighestCard().get(1).getValue());
	}

	@Test
	public void testFlush()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.HEART, CardValue.FOUR);
		Card cardRight = new Card(CardSymbol.HEART, CardValue.KING);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.SEVEN));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.THREE));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.KING));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.SEVEN));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.TEN));

		Hand hand = new Hand(board, cardLeft, cardRight);
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.FLUSH, calculatedHand.getHandType());
	}

	@Test
	public void testStraight()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.HEART, CardValue.FOUR);
		Card cardRight = new Card(CardSymbol.DIAMONDS, CardValue.FIVE);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.SEVEN));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.EIGHT));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.KING));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.SEVEN));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.SIX));

		Hand hand = new Hand(board, cardLeft, cardRight);
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.STRAIGHT, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.HEART, CardValue.EIGHT), calculatedHand.getHighestCard().get(0));
	}

	@Test
	public void testStraight2()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.HEART, CardValue.FOUR);
		Card cardRight = new Card(CardSymbol.DIAMONDS, CardValue.FIVE);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.SEVEN));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.EIGHT));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.KING));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.NINE));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.SIX));

		Hand hand = new Hand(board, cardLeft, cardRight);
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.STRAIGHT, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.HEART, CardValue.NINE), calculatedHand.getHighestCard().get(0));
	}

	@Test
	public void testThreeOfAKind()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.HEART, CardValue.FOUR);
		Card cardRight = new Card(CardSymbol.DIAMONDS, CardValue.FIVE);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.FOUR));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.EIGHT));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.FOUR));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.NINE));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.SIX));

		Hand hand = new Hand(board, cardLeft, cardRight);
		;
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.THREE_OF_A_KIND, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.BACK, CardValue.FOUR), calculatedHand.getHighestCard().get(0));
	}

	@Test
	public void testTwoPairs()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.HEART, CardValue.FOUR);
		Card cardRight = new Card(CardSymbol.DIAMONDS, CardValue.FIVE);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.FOUR));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.EIGHT));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.FIVE));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.NINE));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.SIX));

		Hand hand = new Hand(board, cardLeft, cardRight);
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.TWO_PAIRS, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.BACK, CardValue.FOUR).getValue(), calculatedHand.getHighestCard().get(0).getValue());
		assertEquals(new Card(CardSymbol.BACK, CardValue.FIVE).getValue(), calculatedHand.getHighestCard().get(1).getValue());
	}

	@Test
	public void testPair()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.HEART, CardValue.FOUR);
		Card cardRight = new Card(CardSymbol.DIAMONDS, CardValue.FIVE);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.FOUR));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.EIGHT));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.JACK));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.NINE));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.SIX));

		Hand hand = new Hand(board, cardLeft, cardRight);
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.PAIR, calculatedHand.getHandType());
	}

	@Test
	public void testHighestCard()
	{
		BeddoMischerMain.setBlockOption(BlockOption.NONE);

		Card cardLeft = new Card(CardSymbol.HEART, CardValue.KING);
		Card cardRight = new Card(CardSymbol.DIAMONDS, CardValue.FIVE);

		Board board = new Board();
		board.setCard(0, new Card(CardSymbol.SPADES, CardValue.FOUR));
		board.setCard(1, new Card(CardSymbol.HEART, CardValue.EIGHT));
		board.setCard(2, new Card(CardSymbol.CROSS, CardValue.JACK));
		board.setCard(3, new Card(CardSymbol.HEART, CardValue.NINE));
		board.setCard(4, new Card(CardSymbol.HEART, CardValue.SIX));

		Hand hand = new Hand(board, cardLeft, cardRight);
		CalculatedHand calculatedHand = hand.detectHand();

		assertEquals(HandType.HIGHEST_CARD, calculatedHand.getHandType());
	}
}