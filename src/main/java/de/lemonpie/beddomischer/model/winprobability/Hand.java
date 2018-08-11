package de.lemonpie.beddomischer.model.winprobability;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddocommon.model.card.CardSymbol;
import de.lemonpie.beddocommon.model.card.CardValue;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.HandType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Hand
{
	private Board board;
	private Card cardLeft;
	private Card cardRight;
	private ArrayList<ArrayList<ArrayList<Card>>> allLists;

	public Hand(Board board, Card cardLeft, Card cardRight)
	{
		this.board = board;
		this.cardLeft = cardLeft;
		this.cardRight = cardRight;

		allLists = new ArrayList<>();
		allLists.add(getCustomCardList(true, false));
		allLists.add(getCustomCardList(false, true));
		allLists.add(getCustomCardList(true, true));
	}

	public CalculatedHand detectHand()
	{
		Card highestCard;
		ArrayList<Card> highestCards;

		if(detectRoyalFlush())
			return new CalculatedHand(HandType.ROYAL_FLUSH, Card.EMPTY);

		if(detectStraightFlush())
			return new CalculatedHand(HandType.STRAIGHT_FLUSH, Card.EMPTY);

		highestCard = detectXOfAKind(4);
		if(highestCard != Card.EMPTY)
			return new CalculatedHand(HandType.FOUR_OF_A_KIND, highestCard);

		highestCards = detectFullHouse();
		if(!highestCards.contains(Card.EMPTY))
			return new CalculatedHand(HandType.FULL_HOUSE, highestCards);

		if(detectFlush())
			return new CalculatedHand(HandType.FLUSH, Card.EMPTY);

		highestCard = detectStraight();
		if(highestCard != Card.EMPTY)
			return new CalculatedHand(HandType.STRAIGHT, highestCard);

		highestCard = detectXOfAKind(3);
		if(highestCard != Card.EMPTY)
			return new CalculatedHand(HandType.THREE_OF_A_KIND, highestCard);

		highestCards = detectTwoPairs();
		if(!highestCards.contains(Card.EMPTY))
			return new CalculatedHand(HandType.TWO_PAIRS, highestCards);

		highestCard = detectXOfAKind(2);
		if(highestCard != Card.EMPTY)
			return new CalculatedHand(HandType.PAIR, highestCard);

		return new CalculatedHand(HandType.HIGHEST_CARD, Card.EMPTY);
	}

	private Card detectXOfAKind(int times)
	{
		ArrayList<Integer> occurrences = countOccurrencesOfValues(new ArrayList<>(Arrays.asList(board.getCards())));

		if(cardLeft.getValue().getWeight() == cardRight.getValue().getWeight())
		{
			if(occurrences.get(cardLeft.getValue().getWeight()) == times - 2)
			{
				return new Card(CardSymbol.BACK, cardLeft.getValue());
			}
		}
		else
		{
			if(occurrences.get(cardLeft.getValue().getWeight()) == times - 1)
			{
				return new Card(CardSymbol.BACK, cardLeft.getValue());
			}

			if(occurrences.get(cardRight.getValue().getWeight()) == times - 1)
			{
				return new Card(CardSymbol.BACK, cardRight.getValue());
			}
		}

		return Card.EMPTY;
	}

	private ArrayList<Card> detectTwoPairs()
	{
		ArrayList<Integer> occurrences = countOccurrencesOfValues(new ArrayList<>(Arrays.asList(board.getCards())));
		ArrayList<Card> cardPairs = new ArrayList<>();
		cardPairs.add(Card.EMPTY);
		cardPairs.add(Card.EMPTY);

		if(cardLeft.getValue().getWeight() == cardRight.getValue().getWeight())
		{
			cardPairs.set(0, cardLeft);
			if(occurrences.contains(2))
			{
				cardPairs.set(1, new Card(CardSymbol.BACK, CardValue.fromWeight(occurrences.lastIndexOf(2))));
			}
		}
		else
		{
			if(occurrences.get(cardLeft.getValue().getWeight()) == 1 && occurrences.get(cardRight.getValue().getWeight()) == 1)
			{
				cardPairs.set(0, cardLeft);
				cardPairs.set(1, cardRight);
			}
			else if(occurrences.get(cardLeft.getValue().getWeight()) == 1 && occurrences.contains(2))
			{
				cardPairs.set(0, cardLeft);
				cardPairs.set(1, new Card(CardSymbol.BACK, CardValue.fromWeight(occurrences.lastIndexOf(2))));
			}
			else if(occurrences.get(cardRight.getValue().getWeight()) == 1 && occurrences.contains(2))
			{
				cardPairs.set(0, cardRight);
				cardPairs.set(1, new Card(CardSymbol.BACK, CardValue.fromWeight(occurrences.lastIndexOf(2))));
			}
		}

		return cardPairs;
	}

	private Card detectStraightFromCardList(ArrayList<Card> cardList)
	{
		Card highestCard;
		for(int k = cardList.size() - 1; k >= 0; k--)
		{
			Card currentCard = cardList.get(k);
			// straight is not possible if current card is already below 5
			if(currentCard.getValue().getWeight() < 5)
			{
				break;
			}

			boolean currentRun = true;
			highestCard = currentCard;

			for(int i = 1; i < 5; i++)
			{
				CardValue nextValue = CardValue.fromWeight(currentCard.getValue().getWeight() - i);
				if(!listContainsCardWithValue(cardList, nextValue))
				{
					//ace is allowed on first position before a 2( Ace - 2 - 3 ...)
					if(currentCard.getValue().getWeight() == 2 && listContainsCardWithValue(cardList, CardValue.ACE))
					{
						continue;
					}
					currentRun = false;
					highestCard = Card.EMPTY;
					break;
				}
			}

			if(currentRun)
			{
				return highestCard;
			}
		}

		return Card.EMPTY;
	}

	private Card detectStraight()
	{
		Card highestCard = Card.EMPTY;
		for(ArrayList<ArrayList<Card>> listList : allLists)
		{
			for(ArrayList<Card> currentList : listList)
			{
				Card card = detectStraightFromCardList(currentList);
				if(card.getValue().getWeight() > highestCard.getValue().getWeight())
					highestCard = card;
			}
		}

		return highestCard;
	}

	private boolean detectFlushFromCardList(ArrayList<Card> cardList)
	{
		CardSymbol mostUsedSymbol = getMostUsedSymbol(cardList);

		ArrayList<Card> cardsWithMostUsedSymbol = new ArrayList<>();
		// find all card that shares the most used symbol
		for(Card currentCard : cardList)
		{
			if(currentCard.getSymbol() == mostUsedSymbol)
			{
				cardsWithMostUsedSymbol.add(currentCard);
			}
		}

		return cardsWithMostUsedSymbol.size() == 5;
	}

	private boolean detectFlush()
	{
		for(ArrayList<ArrayList<Card>> listList : allLists)
		{
			for(ArrayList<Card> currentList : listList)
			{
				if(detectFlushFromCardList(currentList))
					return true;
			}
		}

		return false;
	}

	private ArrayList<Card> detectFullHouse()
	{
		ArrayList<Integer> occurrences = countOccurrencesOfValues(new ArrayList<>(Arrays.asList(board.getCards())));
		ArrayList<Card> highestCards = new ArrayList<>();
		highestCards.add(Card.EMPTY);
		highestCards.add(Card.EMPTY);

		if(cardLeft.getValue().getWeight() == cardRight.getValue().getWeight())
		{
			if(occurrences.contains(3))
			{
				highestCards.set(0, new Card(CardSymbol.BACK, CardValue.fromWeight(occurrences.lastIndexOf(3))));
				highestCards.set(1, cardLeft);
			}
			else if(occurrences.get(cardLeft.getValue().getWeight()) == 1 && occurrences.contains(2))
			{
				highestCards.set(0, cardLeft);
				highestCards.set(1, new Card(CardSymbol.BACK, CardValue.fromWeight(occurrences.lastIndexOf(2))));
			}
		}
		else
		{
			if(occurrences.get(cardLeft.getValue().getWeight()) == 2 && occurrences.get(cardRight.getValue().getWeight()) == 1)
			{
				highestCards.set(0, cardLeft);
				highestCards.set(1, cardRight);
			}

			if(occurrences.get(cardLeft.getValue().getWeight()) == 1 && occurrences.get(cardRight.getValue().getWeight()) == 2)
			{
				highestCards.set(0, cardRight);
				highestCards.set(1, cardLeft);
			}
		}

		return highestCards;
	}

	private boolean detectStraightFlushFromCardList(ArrayList<Card> cardList)
	{
		CardSymbol mostUsedSymbol = getMostUsedSymbol(cardList);

		ArrayList<Card> cardsWithMostUsedSymbol = new ArrayList<>();
		// find all card that shares the most used symbol
		for(Card currentCard : cardList)
		{
			if(currentCard.getSymbol() == mostUsedSymbol)
			{
				cardsWithMostUsedSymbol.add(currentCard);
			}
		}

		Card highestCard = Card.EMPTY;

		// straight flush is only possible with at least 5 cards
		if(cardsWithMostUsedSymbol.size() < 5)
			return false;

		for(int k = cardsWithMostUsedSymbol.size() - 1; k >= 0; k--)
		{
			Card currentCard = cardsWithMostUsedSymbol.get(k);
			// straight flush is not possible if current card is already below 5
			if(currentCard.getValue().getWeight() < 5)
			{
				break;
			}

			boolean currentRun = true;

			for(int i = 1; i <= 4; i++)
			{
				Card cardToTest = new Card(mostUsedSymbol, CardValue.fromWeight(currentCard.getValue().getWeight() - i));
				if(!cardsWithMostUsedSymbol.contains(cardToTest))
				{
					currentRun = false;
					break;
				}
			}

			if(currentRun)
			{
				return true;
			}
		}

		return false;
	}

	private boolean detectStraightFlush()
	{
		for(ArrayList<ArrayList<Card>> listList : allLists)
		{
			for(ArrayList<Card> currentList : listList)
			{
				if(detectStraightFlushFromCardList(currentList))
					return true;
			}
		}

		return false;
	}

	private boolean detectRoyalFlushForCardList(ArrayList<Card> cardList)
	{
		if(listContainsCard(cardList, new Card(CardSymbol.HEART, CardValue.TEN))
				&& listContainsCard(cardList, new Card(CardSymbol.HEART, CardValue.JACK))
				&& listContainsCard(cardList, new Card(CardSymbol.HEART, CardValue.QUEEN))
				&& listContainsCard(cardList, new Card(CardSymbol.HEART, CardValue.KING))
				&& listContainsCard(cardList, new Card(CardSymbol.HEART, CardValue.ACE)))
		{
			return true;
		}

		if(listContainsCard(cardList, new Card(CardSymbol.CROSS, CardValue.TEN))
				&& listContainsCard(cardList, new Card(CardSymbol.CROSS, CardValue.JACK))
				&& listContainsCard(cardList, new Card(CardSymbol.CROSS, CardValue.QUEEN))
				&& listContainsCard(cardList, new Card(CardSymbol.CROSS, CardValue.KING))
				&& listContainsCard(cardList, new Card(CardSymbol.CROSS, CardValue.ACE)))
		{
			return true;
		}

		if(listContainsCard(cardList, new Card(CardSymbol.DIAMONDS, CardValue.TEN))
				&& listContainsCard(cardList, new Card(CardSymbol.DIAMONDS, CardValue.JACK))
				&& listContainsCard(cardList, new Card(CardSymbol.DIAMONDS, CardValue.QUEEN))
				&& listContainsCard(cardList, new Card(CardSymbol.DIAMONDS, CardValue.KING))
				&& listContainsCard(cardList, new Card(CardSymbol.DIAMONDS, CardValue.ACE)))
		{
			return true;
		}

		if(listContainsCard(cardList, new Card(CardSymbol.SPADES, CardValue.TEN))
				&& listContainsCard(cardList, new Card(CardSymbol.SPADES, CardValue.JACK))
				&& listContainsCard(cardList, new Card(CardSymbol.SPADES, CardValue.QUEEN))
				&& listContainsCard(cardList, new Card(CardSymbol.SPADES, CardValue.KING))
				&& listContainsCard(cardList, new Card(CardSymbol.SPADES, CardValue.ACE)))
		{
			return true;
		}

		return false;
	}

	private boolean detectRoyalFlush()
	{
		for(ArrayList<ArrayList<Card>> listList : allLists)
		{
			for(ArrayList<Card> currentList : listList)
			{
				if(detectRoyalFlushForCardList(currentList))
					return true;
			}
		}

		return false;
	}

	private ArrayList<ArrayList<Card>> getCustomCardList(boolean useCardLeft, boolean useCardRight)
	{
		ArrayList<ArrayList<Card>> possibleLists = new ArrayList<>();

		if(useCardLeft ^ useCardRight) //XOR
		{
			for(int i = 0; i < 5; i++)
			{
				ArrayList<Card> currentList = new ArrayList<>(Arrays.asList(board.getCards()));
				currentList.remove(i);
				if(useCardLeft)
				{
					currentList.add(cardLeft);
				}
				else
				{
					currentList.add(cardRight);
				}
				Collections.sort(currentList);
				possibleLists.add(currentList);
			}
		}
		else if(useCardLeft && useCardRight)
		{
			for(int i = 0; i < 5; i++)
			{
				for(int k = i + 1; k < 5; k++)
				{
					Card[] boardCards = board.getCards();
					ArrayList<Card> allowedCards = new ArrayList<>();
					for(int j = 0; j < boardCards.length; j++)
					{
						if(j != i && j != k)
						{
							allowedCards.add(boardCards[j]);
						}
					}
					allowedCards.add(cardLeft);
					allowedCards.add(cardRight);
					Collections.sort(allowedCards);
					possibleLists.add(allowedCards);
				}
			}
		}
		else
		{
			possibleLists.add(new ArrayList<>(Arrays.asList(board.getCards())));
		}
		return possibleLists;
	}

	private boolean listContainsCard(ArrayList<Card> cards, Card card)
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

	private boolean listContainsCardWithValue(ArrayList<Card> cards, CardValue value)
	{
		if(value == null)
			return false;

		for(Card currentCard : cards)
		{
			if(currentCard.getValue().getWeight() == value.getWeight())
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

	private ArrayList<Integer> countOccurrencesOfValues(ArrayList<Card> cards)
	{
		ArrayList<Integer> occurrences = new ArrayList<>();
		for(int i = 0; i < 15; i++)
		{
			occurrences.add(0);
		}

		for(Card currentCard : cards)
		{
			int currentIndex = currentCard.getValue().getWeight();
			occurrences.set(currentIndex, occurrences.get(currentIndex) + 1);
		}

		return occurrences;
	}
}