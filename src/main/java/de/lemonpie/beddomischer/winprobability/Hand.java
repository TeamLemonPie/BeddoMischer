package de.lemonpie.beddomischer.winprobability;

import java.util.ArrayList;
import java.util.Collections;

public class Hand
{
	private ArrayList<Card> cards;

	public Hand(ArrayList<Card> cards)
	{
		this.cards = cards;
		Collections.sort(this.cards);
	}

	public Hand(Board board, Card cardLeft, Card cardRight)
	{
		cards = new ArrayList<>();
		cards.addAll(board.getCards());
		cards.add(cardLeft);
		cards.add(cardRight);
		Collections.sort(this.cards);
	}

	public CalculatedHand detectHand()
	{
		Card highestCard = detectRoyalFlush();
		if(!highestCard.isEmpty())
			return new CalculatedHand(HandType.ROYAL_FLUSH, highestCard);

		highestCard = detectStraightFlush();
		if(!highestCard.isEmpty())
			return new CalculatedHand(HandType.STRAIGHT_FLUSH, highestCard);

		highestCard = detectXOfAKind(4);
		if(!highestCard.isEmpty())
			return new CalculatedHand(HandType.FOUR_OF_A_KIND, highestCard);
		
		ArrayList<Card> highestCards = detectFullHouse();		
		if(!highestCards.contains(new Card()))
			return new CalculatedHand(HandType.FULL_HOUSE, highestCards);

		highestCard = detectFlush();
		if(!highestCard.isEmpty())
			return new CalculatedHand(HandType.FLUSH, highestCard);
		
		highestCard = detectStraight();
		if(!highestCard.isEmpty())
			return new CalculatedHand(HandType.STRAIGHT, highestCard);
		
		highestCard = detectXOfAKind(3);
		if(!highestCard.isEmpty())
			return new CalculatedHand(HandType.THREE_OF_A_KIND, highestCard);		
		
		highestCards = detectTwoPairs();
		if(!highestCards.contains(new Card()))
			return new CalculatedHand(HandType.TWO_PAIRS, highestCards);
		
		highestCard = detectXOfAKind(2);
		if(!highestCard.isEmpty())
			return new CalculatedHand(HandType.PAIR, highestCard);

		return new CalculatedHand(HandType.HIGHEST_CARD, detectHighestCard());
	}
	
	private Card detectHighestCard()
	{
		Card highestCard = cards.get(0);
		for(Card currentCard : cards)
		{
			if(currentCard.getValue().getValue() > highestCard.getValue().getValue())
			{
				highestCard = currentCard;
			}
		}
		
		return highestCard;
	}
	
	private Card detectXOfAKind(int times)
	{
		ArrayList<Integer> occurrences = countOccurencesOValues();
		
		if(occurrences.contains(times))
		{
			return new Card(CardSymbol.BACK, CardValue.fromValue(occurrences.lastIndexOf(times)));
		}		
		
		return new Card();
	}
	
	private ArrayList<Card> detectTwoPairs()
	{
		ArrayList<Integer> occurrences = countOccurencesOValues();
		ArrayList<Card> cardPairs = new ArrayList<>();		
		cardPairs.add(new Card());
		cardPairs.add(new Card());
		
		for(int i = 0; i < occurrences.size(); i++)
		{
			if(occurrences.get(i) == 2)
			{
				CardValue curentValue = CardValue.fromValue(i);
				if(curentValue.getValue() > cardPairs.get(0).getValue().getValue())
				{
					cardPairs.set(1, cardPairs.get(0));
					cardPairs.set(0, new Card(CardSymbol.BACK, curentValue));
				}
			}
		}
		
		return cardPairs;
	}
	
	private Card detectStraight()
	{
		Card highestCard = new Card();
		
		for(int k = cards.size() - 1; k >= 0; k--)
		{
			Card currentCard = cards.get(k);
			// straight is not possible if current card is already below 5
			if(currentCard.getValue().getValue() < 5)
			{
				break;
			}

			boolean currentRun = true;
			highestCard = currentCard;

			for(int i = 1; i < 4; i++)
			{
				CardValue nextValue = CardValue.fromValue(currentCard.getValue().getValue() - i);
				if(!listContainsCardWithValue(nextValue))
				{
					currentRun = false;
					highestCard = new Card();
					break;
				}
			}

			if(currentRun)
			{
				return highestCard;
			}
		}

		return highestCard;
	}
		
	private Card detectFlush()
	{
		CardSymbol mostUsedSymbol = getMostUsedSymbol(cards);

		ArrayList<Card> cardsWithMostUsedSymbol = new ArrayList<>();
		// find all card that shares the most used symbol
		for(Card currentCard : cards)
		{
			if(currentCard.getSymbol() == mostUsedSymbol)
			{
				cardsWithMostUsedSymbol.add(currentCard);
			}
		}

		if(cardsWithMostUsedSymbol.size() == 5)
		{
			Card highestCard = cardsWithMostUsedSymbol.get(0);
			for(Card currentCard : cardsWithMostUsedSymbol)
			{
				if(currentCard.getValue().getValue() > highestCard.getValue().getValue())
				{
					highestCard = currentCard;
				}
			}
			
			return highestCard;
		}
		
		return new Card();
	}
	
	private ArrayList<Card> detectFullHouse()
	{
		ArrayList<Integer> occurrences = countOccurencesOValues();
		
		if(occurrences.contains(3) && occurrences.contains(2))
		{
			ArrayList<Card> highestCards = new ArrayList<>();
			//first card is type of three of a kind
			highestCards.add(new Card(CardSymbol.BACK, CardValue.fromValue(occurrences.lastIndexOf(3))));
			//second card type of pair
			highestCards.add(new Card(CardSymbol.BACK, CardValue.fromValue(occurrences.lastIndexOf(2))));
			return highestCards;
		}
		
		ArrayList<Card> highestCards = new ArrayList<>();
		highestCards.add(new Card());
		return highestCards;
	}

	private Card detectStraightFlush()
	{
		CardSymbol mostUsedSymbol = getMostUsedSymbol(cards);

		ArrayList<Card> cardsWithMostUsedSymbol = new ArrayList<>();
		// find all card that shares the most used symbol
		for(Card currentCard : cards)
		{
			if(currentCard.getSymbol() == mostUsedSymbol)
			{
				cardsWithMostUsedSymbol.add(currentCard);
			}
		}

		Card highestCard = new Card();

		// straight flush is only possible with at least 5 cards
		if(cardsWithMostUsedSymbol.size() < 5)
			return highestCard;

		for(int k = cardsWithMostUsedSymbol.size() - 1; k >= 0; k--)
		{		
			Card currentCard = cardsWithMostUsedSymbol.get(k);
			// straight flush is not possible if current card is already below 5
			if(currentCard.getValue().getValue() < 5)
			{
				break;
			}

			boolean currentRun = true;
			highestCard = currentCard;

			for(int i = 1; i < 4; i++)
			{
				Card cardToTest = new Card(mostUsedSymbol, CardValue.fromValue(currentCard.getValue().getValue() - i));
				if(!cardsWithMostUsedSymbol.contains(cardToTest))
				{
					currentRun = false;
					highestCard = new Card();
					break;
				}
			}

			if(currentRun)
			{
				return highestCard;
			}
		}

		return highestCard;
	}

	private Card detectRoyalFlush()
	{
		if(listContainsCard(new Card(CardSymbol.HEART, CardValue.TEN)) 
				&& listContainsCard(new Card(CardSymbol.HEART, CardValue.JACK)) 
				&& listContainsCard(new Card(CardSymbol.HEART, CardValue.QUEEN)) 
				&& listContainsCard(new Card(CardSymbol.HEART, CardValue.KING))
				&& listContainsCard(new Card(CardSymbol.HEART, CardValue.ACE)))
		{
			return new Card(CardSymbol.HEART, CardValue.ACE);
		}

		if(listContainsCard(new Card(CardSymbol.CROSS, CardValue.TEN))
				&& listContainsCard(new Card(CardSymbol.CROSS, CardValue.JACK)) 
				&& listContainsCard(new Card(CardSymbol.CROSS, CardValue.QUEEN)) 
				&& listContainsCard(new Card(CardSymbol.CROSS, CardValue.KING))
				&& listContainsCard(new Card(CardSymbol.CROSS, CardValue.ACE)))
		{
			return new Card(CardSymbol.CROSS, CardValue.ACE);
		}

		if(listContainsCard(new Card(CardSymbol.DIAMONDS, CardValue.TEN)) 
				&& listContainsCard(new Card(CardSymbol.DIAMONDS, CardValue.JACK)) 
				&& listContainsCard(new Card(CardSymbol.DIAMONDS, CardValue.QUEEN)) 
				&& listContainsCard(new Card(CardSymbol.DIAMONDS, CardValue.KING))
				&& listContainsCard(new Card(CardSymbol.DIAMONDS, CardValue.ACE)))
		{
			return new Card(CardSymbol.DIAMONDS, CardValue.ACE);
		}

		if(listContainsCard(new Card(CardSymbol.SPADES, CardValue.TEN)) 
				&& listContainsCard(new Card(CardSymbol.SPADES, CardValue.JACK)) 
				&& listContainsCard(new Card(CardSymbol.SPADES, CardValue.QUEEN)) 
				&& listContainsCard(new Card(CardSymbol.SPADES, CardValue.KING))
				&& listContainsCard(new Card(CardSymbol.SPADES, CardValue.ACE)))
		{
			return new Card(CardSymbol.SPADES, CardValue.ACE);
		}

		return new Card();
	}

	private boolean listContainsCard(Card card)
	{
		for(Card currentCard : cards)
		{
			if(currentCard.equals(card))
			{
				return true;
			}
		}

		return false;
	}
	
	private boolean listContainsCardWithValue(CardValue value)
	{
		for(Card currentCard : cards)
		{
			if(currentCard.getValue() == value)
			{
				return true;
			}
		}

		return false;
	}

	private CardSymbol getMostUsedSymbol(ArrayList<Card> cardList)
	{
		ArrayList<Integer> occurrences = new ArrayList<>();
		for(int i = 0; i < 4; i++)
		{
			occurrences.add(0);
		}

		for(Card currentCard : cardList)
		{
			switch(currentCard.getSymbol())
			{
				case CROSS:
					occurrences.set(0, occurrences.get(0) + 1);
					break;
				case DIAMONDS:
					occurrences.set(1, occurrences.get(1) + 1);
					break;
				case HEART:
					occurrences.set(2, occurrences.get(2) + 1);
					break;
				case SPADES:
					occurrences.set(3, occurrences.get(3) + 1);
					break;
				default:
					break;
			}
		}

		int max = Collections.max(occurrences);
		switch(occurrences.indexOf(max))
		{
			case 0:
				return CardSymbol.CROSS;
			case 1:
				return CardSymbol.DIAMONDS;
			case 2:
				return CardSymbol.HEART;
			case 3:
				return CardSymbol.SPADES;
		}

		return null;
	}
	
	private ArrayList<Integer> countOccurencesOValues()
	{
		ArrayList<Integer> occurrences = new ArrayList<>();
		for(int i = 0; i < 15; i++)
		{
			occurrences.add(0);
		}
		
		for(Card currentCard : cards)
		{
			int currentIndex = currentCard.getValue().getValue();
			occurrences.set(currentIndex, occurrences.get(currentIndex) + 1);
		}
		
		return occurrences;
	}
}