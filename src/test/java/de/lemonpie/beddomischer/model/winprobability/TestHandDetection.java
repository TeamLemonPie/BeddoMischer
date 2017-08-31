package de.lemonpie.beddomischer.model.winprobability;

import de.lemonpie.beddomischer.model.HandType;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.card.CardSymbol;
import de.lemonpie.beddomischer.model.card.CardValue;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestHandDetection
{
	@Test
	public void testRoyalFlush()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.HEART, CardValue.ACE));
		cards.add(new Card(CardSymbol.HEART, CardValue.QUEEN));
		cards.add(new Card(CardSymbol.HEART, CardValue.TEN));
		cards.add(new Card(CardSymbol.SPADES, CardValue.SEVEN));
		cards.add(new Card(CardSymbol.HEART, CardValue.KING));
		cards.add(new Card(CardSymbol.CROSS, CardValue.KING));
		cards.add(new Card(CardSymbol.HEART, CardValue.JACK));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.ROYAL_FLUSH, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.HEART, CardValue.ACE), calculatedHand.getHighestCard().get(0));
	}
	
	@Test
	public void testStraightFlush()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.HEART, CardValue.NINE));
		cards.add(new Card(CardSymbol.HEART, CardValue.QUEEN));
		cards.add(new Card(CardSymbol.HEART, CardValue.TEN));
		cards.add(new Card(CardSymbol.SPADES, CardValue.SEVEN));
		cards.add(new Card(CardSymbol.HEART, CardValue.KING));
		cards.add(new Card(CardSymbol.CROSS, CardValue.KING));
		cards.add(new Card(CardSymbol.HEART, CardValue.JACK));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.STRAIGHT_FLUSH, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.HEART, CardValue.KING), calculatedHand.getHighestCard().get(0));
	}
	
	@Test
	public void testFourOfAKind()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.DIAMONDS, CardValue.KING));
		cards.add(new Card(CardSymbol.SPADES, CardValue.KING));
		cards.add(new Card(CardSymbol.HEART, CardValue.TEN));
		cards.add(new Card(CardSymbol.SPADES, CardValue.SEVEN));
		cards.add(new Card(CardSymbol.HEART, CardValue.KING));
		cards.add(new Card(CardSymbol.CROSS, CardValue.KING));
		cards.add(new Card(CardSymbol.HEART, CardValue.JACK));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.FOUR_OF_A_KIND, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.BACK, CardValue.KING), calculatedHand.getHighestCard().get(0));
	}

	@Test
	public void testFullHouse()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.DIAMONDS, CardValue.KING));
		cards.add(new Card(CardSymbol.SPADES, CardValue.KING));
		cards.add(new Card(CardSymbol.HEART, CardValue.TEN));
		cards.add(new Card(CardSymbol.SPADES, CardValue.SEVEN));
		cards.add(new Card(CardSymbol.HEART, CardValue.THREE));
		cards.add(new Card(CardSymbol.CROSS, CardValue.KING));
		cards.add(new Card(CardSymbol.HEART, CardValue.SEVEN));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.FULL_HOUSE, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.BACK, CardValue.KING), calculatedHand.getHighestCard().get(0));
		assertEquals(new Card(CardSymbol.BACK, CardValue.SEVEN), calculatedHand.getHighestCard().get(1));
	}
	
	@Test
	public void testFlush()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.HEART, CardValue.FOUR));
		cards.add(new Card(CardSymbol.HEART, CardValue.KING));
		cards.add(new Card(CardSymbol.HEART, CardValue.TEN));
		cards.add(new Card(CardSymbol.SPADES, CardValue.SEVEN));
		cards.add(new Card(CardSymbol.HEART, CardValue.THREE));
		cards.add(new Card(CardSymbol.CROSS, CardValue.KING));
		cards.add(new Card(CardSymbol.HEART, CardValue.SEVEN));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.FLUSH, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.HEART, CardValue.KING), calculatedHand.getHighestCard().get(0));
	}
	
	@Test
	public void testStraight()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.HEART, CardValue.FOUR));
		cards.add(new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
		cards.add(new Card(CardSymbol.HEART, CardValue.EIGHT));
		cards.add(new Card(CardSymbol.SPADES, CardValue.SEVEN));
		cards.add(new Card(CardSymbol.HEART, CardValue.SIX));
		cards.add(new Card(CardSymbol.CROSS, CardValue.KING));
		cards.add(new Card(CardSymbol.HEART, CardValue.SEVEN));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.STRAIGHT, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.HEART, CardValue.EIGHT), calculatedHand.getHighestCard().get(0));
	}
	
	@Test
	public void testStraight2()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.HEART, CardValue.FOUR));
		cards.add(new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
		cards.add(new Card(CardSymbol.HEART, CardValue.EIGHT));
		cards.add(new Card(CardSymbol.SPADES, CardValue.SEVEN));
		cards.add(new Card(CardSymbol.HEART, CardValue.SIX));
		cards.add(new Card(CardSymbol.CROSS, CardValue.KING));
		cards.add(new Card(CardSymbol.HEART, CardValue.NINE));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.STRAIGHT, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.HEART, CardValue.NINE), calculatedHand.getHighestCard().get(0));
	}
	
	@Test
	public void testThreeOfAKind()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.HEART, CardValue.FOUR));
		cards.add(new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
		cards.add(new Card(CardSymbol.HEART, CardValue.EIGHT));
		cards.add(new Card(CardSymbol.SPADES, CardValue.FOUR));
		cards.add(new Card(CardSymbol.HEART, CardValue.SIX));
		cards.add(new Card(CardSymbol.CROSS, CardValue.FOUR));
		cards.add(new Card(CardSymbol.HEART, CardValue.NINE));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.THREE_OF_A_KIND, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.BACK, CardValue.FOUR), calculatedHand.getHighestCard().get(0));
	}
	
	@Test
	public void testTwoPairs()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.HEART, CardValue.FOUR));
		cards.add(new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
		cards.add(new Card(CardSymbol.HEART, CardValue.EIGHT));
		cards.add(new Card(CardSymbol.SPADES, CardValue.FOUR));
		cards.add(new Card(CardSymbol.HEART, CardValue.SIX));
		cards.add(new Card(CardSymbol.CROSS, CardValue.FIVE));
		cards.add(new Card(CardSymbol.HEART, CardValue.NINE));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.TWO_PAIRS, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.BACK, CardValue.FIVE), calculatedHand.getHighestCard().get(0));
		assertEquals(new Card(CardSymbol.BACK, CardValue.FOUR), calculatedHand.getHighestCard().get(1));
	}
	
	@Test
	public void testPair()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.HEART, CardValue.FOUR));
		cards.add(new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
		cards.add(new Card(CardSymbol.HEART, CardValue.EIGHT));
		cards.add(new Card(CardSymbol.SPADES, CardValue.FOUR));
		cards.add(new Card(CardSymbol.HEART, CardValue.SIX));
		cards.add(new Card(CardSymbol.CROSS, CardValue.JACK));
		cards.add(new Card(CardSymbol.HEART, CardValue.NINE));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.PAIR, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.BACK, CardValue.FOUR), calculatedHand.getHighestCard().get(0));
	}
	
	@Test
	public void testHighetsCard()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(CardSymbol.HEART, CardValue.KING));
		cards.add(new Card(CardSymbol.DIAMONDS, CardValue.FIVE));
		cards.add(new Card(CardSymbol.HEART, CardValue.EIGHT));
		cards.add(new Card(CardSymbol.SPADES, CardValue.FOUR));
		cards.add(new Card(CardSymbol.HEART, CardValue.SIX));
		cards.add(new Card(CardSymbol.CROSS, CardValue.JACK));
		cards.add(new Card(CardSymbol.HEART, CardValue.NINE));
		
		Hand hand = new Hand(cards);
		CalculatedHand calculatedHand = hand.detectHand();
		
		assertEquals(HandType.HIGHEST_CARD, calculatedHand.getHandType());
		assertEquals(new Card(CardSymbol.HEART, CardValue.KING), calculatedHand.getHighestCard().get(0));
	}
}