package de.lemonpie.pokerwinprobability.logic;

import java.util.ArrayList;


public class Board
{
	private ArrayList<Card> cards;

	public Board()
    {      
        cards = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            cards.add(new Card());
        }
    }
    
    public void setCard(Card card, int index)
    {
		cards.set(index, card);
    }

	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	public int getNumberOfMissingCards()
	{
		int numberOfMissingCards = 0;
		
		for(int i = 0; i < 5; i++)
        {
			if(cards.get(i).isEmpty())
			{
				numberOfMissingCards++;
			}
        }
		
		return numberOfMissingCards;
	}

	@Override
	public String toString()
	{
		return "Board [cards=" + cards + "]";
	}
}