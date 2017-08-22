package de.lemonpie.pokerwinprobability.logic;

import java.util.ArrayList;
import java.util.Random;

public class Calculation
{
	private ArrayList<Player> players;
	private Board board;

	public Calculation(ArrayList<Player> players, Board board)
	{
		this.players = players;
		this.board = board;
	}

	public ArrayList<Double> calculate(int numberOfRounds)
	{
		ArrayList<Double> probabilities = new ArrayList<>();
		ArrayList<Integer> wonRounds = new ArrayList<>();
		for(int i = 0; i < players.size(); i++)
		{
			probabilities.add(0.0);
			wonRounds.add(0);
		}

		ArrayList<Card> remainingDeck = getRemainingDeck();
		int numberOfMissingCardsInBoard = board.getNumberOfMissingCards();

		if(numberOfMissingCardsInBoard == 0)
		{
			numberOfRounds = 1;
			wonRounds = calculateOneRound(wonRounds, new ArrayList<>(remainingDeck), numberOfMissingCardsInBoard);
		}
		else
		{
			for(int i = 0; i < numberOfRounds; i++)
			{
				wonRounds = calculateOneRound(wonRounds, new ArrayList<>(remainingDeck), numberOfMissingCardsInBoard);
			}
		}

		for(int i = 0; i < players.size(); i++)
		{
			probabilities.set(i, (double)wonRounds.get(i) / numberOfRounds);
		}

		return probabilities;
	}

	private ArrayList<Integer> calculateOneRound(ArrayList<Integer> wonRounds, ArrayList<Card> remainingDeck, int numberOfMissingCardsInBoard)
	{
		Board fullBoard = new Board();
		for(int i = 0; i < 5; i++)
		{
			if(i < 5 - numberOfMissingCardsInBoard)
			{
				fullBoard.setCard(board.getCards().get(i), i);
			}
			else
			{
				Random random = new Random();
				int randomIndex = random.nextInt(remainingDeck.size());
				fullBoard.setCard(remainingDeck.get(randomIndex), i);
				remainingDeck.remove(randomIndex);
			}
		}

		ArrayList<Integer> winners = calculateWinners(fullBoard);
		for(Integer currentIndex : winners)
		{
			wonRounds.set(currentIndex, wonRounds.get(currentIndex) + 1);
		}

		return wonRounds;
	}

	private ArrayList<Integer> calculateWinners(Board fullBoard)
	{
		for(Player currentPlayer : players)
		{
			Hand hand = new Hand(fullBoard, currentPlayer.getCardLeft(), currentPlayer.getCardRight());
			currentPlayer.setCalculatedHand(hand.detectHand());
		}

		//compare
		
		//royal flush
		ArrayList<Integer> winnerIndices = new ArrayList<>();
		winnerIndices = getHandsFromPlayers(HandType.ROYAL_FLUSH);
		if(winnerIndices.size() > 0)
		{
			return winnerIndices;
		}
		
		//straight flush
		winnerIndices = getHandsFromPlayers(HandType.STRAIGHT_FLUSH);
		if(winnerIndices.size() > 0)
		{
			return calculateWinnerIndices(winnerIndices, HandType.STRAIGHT_FLUSH);				
		}
		
		//four of a kind
		winnerIndices = getHandsFromPlayers(HandType.FOUR_OF_A_KIND);
		if(winnerIndices.size() > 0)
		{
			return calculateWinnerIndices(winnerIndices, HandType.FOUR_OF_A_KIND);
		}
		
		//full house	
		winnerIndices = getHandsFromPlayers(HandType.FULL_HOUSE);
		if(winnerIndices.size() > 0)
		{
			return calculateWinnerIndices(winnerIndices, HandType.FULL_HOUSE);
		}
		
		//flush
		winnerIndices = getHandsFromPlayers(HandType.FLUSH);
		if(winnerIndices.size() > 0)
		{
			return calculateWinnerIndices(winnerIndices, HandType.FLUSH);
		}
		
		//straight
		winnerIndices = getHandsFromPlayers(HandType.STRAIGHT);
		if(winnerIndices.size() > 0)
		{
			return calculateWinnerIndices(winnerIndices, HandType.STRAIGHT);
		}
		
		//three of a kind
		winnerIndices = getHandsFromPlayers(HandType.THREE_OF_A_KIND);
		if(winnerIndices.size() > 0)
		{
			return calculateWinnerIndices(winnerIndices, HandType.THREE_OF_A_KIND);
		}
		
		//two pairs
		winnerIndices = getHandsFromPlayers(HandType.THREE_OF_A_KIND);
		if(winnerIndices.size() > 0)
		{
			return calculateWinnerIndices(winnerIndices, HandType.THREE_OF_A_KIND);
		}
		
		//pair
		winnerIndices = getHandsFromPlayers(HandType.PAIR);
		if(winnerIndices.size() > 0)
		{
			return calculateWinnerIndices(winnerIndices, HandType.PAIR);
		}
		
		//highest card
		winnerIndices = getHandsFromPlayers(HandType.HIGHEST_CARD);
		if(winnerIndices.size() > 0)
		{
			return calculateWinnerIndices(winnerIndices, HandType.HIGHEST_CARD);
		}

		return winnerIndices;
	}
	
	private ArrayList<Integer> calculateWinnerBasedOnHighestCard(ArrayList<Integer> winnerIndices, int highestCardIndex)
	{
		ArrayList<Integer> returnList = new ArrayList<>();
		returnList.add(winnerIndices.get(0));
		
		for(int i = 1; i < winnerIndices.size(); i++)
		{			
			CardValue highestCardValue = players.get(returnList.get(0)).getCalculatedHand().getHighestCard().get(highestCardIndex).getValue();
			ArrayList<Card> playerHighestCards = players.get(i).getCalculatedHand().getHighestCard();
			if(playerHighestCards.size() > highestCardIndex)
			{				
				CardValue playerCardValue = playerHighestCards.get(highestCardIndex).getValue();
				if(playerCardValue.getValue() > highestCardValue.getValue())
				{
					returnList = new ArrayList<>();				
					returnList.add(i);
				}
				else if(playerCardValue.getValue() == highestCardValue.getValue())
				{
					if(!returnList.contains(i))
						returnList.add(i);
				}
			}
		}
	
		return returnList;
	}
	
	private ArrayList<Integer> calculateWinnerIndices(ArrayList<Integer> winnerIndices, HandType handType)
	{
		if(winnerIndices.size() > 1)
		{			
			ArrayList<Integer> returnList = calculateWinnerBasedOnHighestCard(winnerIndices, 0);			
			
			if(returnList.size() > 1 && (handType == HandType.FULL_HOUSE || handType == HandType.TWO_PAIRS))
			{
				return calculateWinnerBasedOnHighestCard(returnList, 1);
			}
			else
			{
				return returnList;
			}
		}
		else				
		{
			ArrayList<Integer> returnList = new ArrayList<>();
			returnList.add(winnerIndices.get(0));
			return returnList;
		}		
	}

	private ArrayList<Integer> getHandsFromPlayers(HandType handtype)
	{
		ArrayList<Integer> hands = new ArrayList<>();

		for(int i = 0; i < players.size(); i++)
		{
			if(players.get(i).getCalculatedHand().getHandType() == handtype)
			{
				hands.add(i);
			}
		}

		return hands;
	}

	private ArrayList<Card> getRemainingDeck()
	{
		ArrayList<Card> fullDeck = getFullDeck();
		for(Player currentPlayer : players)
		{
			fullDeck.remove(currentPlayer.getCardLeft());
			fullDeck.remove(currentPlayer.getCardRight());
		}

		fullDeck.removeAll(board.getCards());

		return fullDeck;
	}

	private ArrayList<Card> getFullDeck()
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.addAll(getAllCardsWithSameSymbol(CardSymbol.HEART));
		cards.addAll(getAllCardsWithSameSymbol(CardSymbol.CROSS));
		cards.addAll(getAllCardsWithSameSymbol(CardSymbol.DIAMONDS));
		cards.addAll(getAllCardsWithSameSymbol(CardSymbol.SPADES));

		return cards;
	}

	private ArrayList<Card> getAllCardsWithSameSymbol(CardSymbol symbol)
	{
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(symbol, CardValue.TWO));
		cards.add(new Card(symbol, CardValue.THREE));
		cards.add(new Card(symbol, CardValue.FOUR));
		cards.add(new Card(symbol, CardValue.FIVE));
		cards.add(new Card(symbol, CardValue.SIX));
		cards.add(new Card(symbol, CardValue.SEVEN));
		cards.add(new Card(symbol, CardValue.EIGHT));
		cards.add(new Card(symbol, CardValue.NINE));
		cards.add(new Card(symbol, CardValue.TEN));
		cards.add(new Card(symbol, CardValue.JACK));
		cards.add(new Card(symbol, CardValue.QUEEN));
		cards.add(new Card(symbol, CardValue.KING));
		cards.add(new Card(symbol, CardValue.ACE));

		return cards;
	}
}