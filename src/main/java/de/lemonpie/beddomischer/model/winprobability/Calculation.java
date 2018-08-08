package de.lemonpie.beddomischer.model.winprobability;

import de.lemonpie.beddocommon.card.Card;
import de.lemonpie.beddocommon.card.CardSymbol;
import de.lemonpie.beddocommon.card.CardValue;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.HandType;
import de.lemonpie.beddomischer.model.Player;
import logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Calculation
{
	private List<Player> players;
	private Board board;

	public Calculation(List<Player> players, Board board)
	{
		this.players = players;
		this.board = board;
	}

	public List<Double> calculate(int numberOfRounds)
	{
		List<Double> probabilities = new ArrayList<>();
		List<Integer> wonRounds = new ArrayList<>();
		for(int i = 0; i < players.size(); i++)
		{
			probabilities.add(0.0);
			wonRounds.add(0);
		}

		ArrayList<Card> remainingDeck = getRemainingDeck();
		int numberOfMissingCardsInBoard = board.getNumberOfMissingCards();

		if(numberOfMissingCardsInBoard == 0)
		{
			wonRounds = calculateOneRound(wonRounds, new ArrayList<>(remainingDeck), numberOfMissingCardsInBoard);
		}
		else
		{
			for(int i = 0; i < numberOfRounds; i++)
			{
				wonRounds = calculateOneRound(wonRounds, new ArrayList<>(remainingDeck), numberOfMissingCardsInBoard);
			}
		}

		int totalWins = 0;
		for(Integer wonRound : wonRounds)
		{
			totalWins += wonRound;
		}

		for(int i = 0; i < players.size(); i++)
		{
			probabilities.set(i, (double) wonRounds.get(i) / totalWins);
		}

		return checkAndCorrectProbabilities(probabilities);
	}

	private List<Double> checkAndCorrectProbabilities(List<Double> probabilities)
	{
		// sum of win probabilities should equal 1.0
		double sum = 0;
		for(double currentProbability : probabilities)
		{
			sum += currentProbability;
		}

		// correct win probabilities if necessary
		double difference = sum - 1.0;
		double maxValue = getMaxValue(probabilities);
		if(difference > 0)
		{
			double newValue = maxValue - difference;
			Logger.debug("Corrected win probability for player " + probabilities.indexOf(maxValue) + " from: " + maxValue + " to: " + newValue);
			probabilities.set(probabilities.indexOf(maxValue), newValue);
		}
		else if(difference < 0)
		{
			double newValue = maxValue + difference;
			Logger.debug("Corrected win probability for player " + probabilities.indexOf(maxValue) + " from: " + maxValue + " to: " + newValue);
			probabilities.set(probabilities.indexOf(maxValue), newValue);
		}

		return probabilities;
	}

	private Double getMaxValue(List<Double> values)
	{
		double max = 0;
		for(double value : values)
		{
			if(value > max)
			{
				max = value;
			}
		}
		return max;
	}

	private List<Integer> calculateOneRound(List<Integer> wonRounds, List<Card> remainingDeck, int numberOfMissingCardsInBoard)
	{
		Board fullBoard = new Board();
		for(int i = 0; i < 5; i++)
		{
			if(i < 5 - numberOfMissingCardsInBoard)
			{
				fullBoard.setCard(i, board.getCard(i));
			}
			else
			{
				Random random = new Random();
				int randomIndex = random.nextInt(remainingDeck.size());
				fullBoard.setCard(i, remainingDeck.get(randomIndex));
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
		ArrayList<Integer> winnerIndices = getHandsFromPlayers(HandType.ROYAL_FLUSH);
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
		winnerIndices = getHandsFromPlayers(HandType.TWO_PAIRS);
		if(winnerIndices.size() > 0)
		{
			return calculateWinnerIndices(winnerIndices, HandType.TWO_PAIRS);
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

	private ArrayList<Integer> calculateWinnerBasedOnHighestCard(ArrayList<Integer> winnerIndices, boolean useHighestCard)
	{
		ArrayList<Integer> returnList = new ArrayList<>();
		returnList.add(winnerIndices.get(0));

		for(int i = 1; i < winnerIndices.size(); i++)
		{
			Card highestCard = useHighestCard ? players.get(winnerIndices.get(i)).getHighestCard() : players.get(winnerIndices.get(i)).getLowestCard();
			Card winnerCard = useHighestCard ? players.get(returnList.get(0)).getHighestCard() : players.get(returnList.get(0)).getLowestCard();
			if(highestCard.getValue().getWeight() > winnerCard.getValue().getWeight())
			{
				returnList = new ArrayList<>();
				returnList.add(winnerIndices.get(i));
			}
			else if(highestCard.getValue().getWeight() == winnerCard.getValue().getWeight())
			{
				returnList.add(winnerIndices.get(i));
			}
		}

		return returnList;
	}

	private ArrayList<Integer> calculateWinnerBasedOnCalculatedCards(ArrayList<Integer> winnerIndices, boolean useHighestCard)
	{
		ArrayList<Integer> returnList = new ArrayList<>();
		returnList.add(winnerIndices.get(0));

		for(int i = 1; i < winnerIndices.size(); i++)
		{
			Card highestCard = useHighestCard ? players.get(winnerIndices.get(i)).getCalculatedHand().getHighestCard().get(0) : players.get(winnerIndices.get(i)).getCalculatedHand().getHighestCard().get(1);
			Card winnerCard = useHighestCard ? players.get(returnList.get(0)).getCalculatedHand().getHighestCard().get(0) : players.get(returnList.get(0)).getCalculatedHand().getHighestCard().get(1);
			if(highestCard.getValue().getWeight() > winnerCard.getValue().getWeight())
			{
				returnList = new ArrayList<>();
				returnList.add(winnerIndices.get(i));
			}
			else if(highestCard.getValue().getWeight() == winnerCard.getValue().getWeight())
			{
				returnList.add(winnerIndices.get(i));
			}
		}

		return returnList;
	}

	private ArrayList<Integer> calculateWinnerBasedOnStraight(ArrayList<Integer> winnerIndices)
	{
		ArrayList<Integer> returnList = new ArrayList<>();
		returnList.add(winnerIndices.get(0));

		for(int i = 1; i < winnerIndices.size(); i++)
		{
			Card highestCard = players.get(winnerIndices.get(i)).getCalculatedHand().getHighestCard().get(0);
			Card winnerCard = players.get(returnList.get(0)).getCalculatedHand().getHighestCard().get(0);
			if(highestCard.getValue().getWeight() > winnerCard.getValue().getWeight())
			{
				returnList = new ArrayList<>();
				returnList.add(winnerIndices.get(i));
			}
			else if(highestCard.getValue().getWeight() == winnerCard.getValue().getWeight())
			{
				returnList.add(winnerIndices.get(i));
			}
		}

		return returnList;
	}

	private ArrayList<Integer> calculateWinnerBasedOnPair(ArrayList<Integer> winnerIndices)
	{
		ArrayList<Integer> returnList = new ArrayList<>();
		returnList.add(winnerIndices.get(0));

		// check if all pairs are equal and get highest pair
		CardValue pairValue = players.get(winnerIndices.get(0)).getCalculatedHand().getHighestCard().get(0).getValue();
		CardValue highestPairValue = pairValue;
		boolean allPairsEqual = true;
		for(int i = 1; i < winnerIndices.size(); i++)
		{
			CardValue currentPairValue = players.get(winnerIndices.get(i)).getCalculatedHand().getHighestCard().get(0).getValue();

			if(!currentPairValue.equals(pairValue))
			{
				allPairsEqual = false;
			}

			if(currentPairValue.getWeight() > highestPairValue.getWeight())
			{
				highestPairValue = currentPairValue;
			}
		}

		if(allPairsEqual)
		{
			return calculateWinnerBasedOnHighestCard(winnerIndices, true);
		}
		else
		{
			// if pairs are different then highest pair(s) win
			returnList = new ArrayList<>();
			for(Integer winnerIndice : winnerIndices)
			{
				if(players.get(winnerIndice).getCalculatedHand().getHighestCard().get(0).getValue().equals(highestPairValue))
				{
					returnList.add(winnerIndice);
				}
			}
		}

		return returnList;
	}

	private ArrayList<Integer> calculateWinnerIndices(ArrayList<Integer> winnerIndices, HandType handType)
	{
		if(winnerIndices.size() > 1)
		{
			ArrayList<Integer> winners;
			switch(handType)
			{
				case FULL_HOUSE:
					winners = calculateWinnerBasedOnCalculatedCards(winnerIndices, true);
					return winners.size() > 1 ? calculateWinnerBasedOnCalculatedCards(winners, false) : winners;
				case STRAIGHT:
					winners = calculateWinnerBasedOnStraight(winnerIndices);
					return winners.size() > 1 ? calculateWinnerBasedOnHighestCard(winners, true) : winners;
				case THREE_OF_A_KIND:
					winners = calculateWinnerBasedOnHighestCard(winnerIndices, true);
					return winners.size() > 1 ? calculateWinnerBasedOnHighestCard(winners, false) : winners;
				// rules state that three of a kind build with a pair in hand wins over a pair in board + one card from player deck
				case TWO_PAIRS:
					winners = calculateWinnerBasedOnCalculatedCards(winnerIndices, true);
					return winners.size() > 1 ? calculateWinnerBasedOnCalculatedCards(winners, false) : winners;
				case PAIR:
					winners = calculateWinnerBasedOnPair(winnerIndices);
					return winners.size() > 1 ? calculateWinnerBasedOnHighestCard(winners, true) : winners;
				default:
					winners = calculateWinnerBasedOnHighestCard(winnerIndices, true);
					return winners.size() > 1 ? calculateWinnerBasedOnHighestCard(winners, false) : winners;
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

		fullDeck.removeAll(Arrays.asList(board.getCards()));

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