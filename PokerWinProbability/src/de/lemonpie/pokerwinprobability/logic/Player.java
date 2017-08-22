package de.lemonpie.pokerwinprobability.logic;


public class Player
{
    private String name;
    private Card cardLeft;
    private Card cardRight;
    private CalculatedHand calculatedHand;

	public Player()
    {
    	this.name = "";
		this.cardLeft = new Card();
		this.cardRight = new Card();
    }
    
    public Player(String name) 
    {        
        this.name = name;
        this.cardLeft = new Card();
        this.cardRight = new Card();
    }    
    
    public void setCardLeft(Card card)
    {
        this.cardLeft = card;
    }

    public void setCardRight(Card card)
    {
        this.cardRight = card;
    }
    
    public void resetCards()
    {
    	cardLeft = new Card();
    	cardRight = new Card();
    }
 
    public String getName() 
    {
        return name;
    }

    public void setName(String name)
	{
		this.name = name;
	}
	
    public Card getCardLeft() 
    {
        return cardLeft;
    }

    public Card getCardRight() 
    {
        return cardRight;
    }
    
    public String getCode()
    {
    	String leftCardString = cardLeft.getSymbol().getShortCode() + cardLeft.getValue().getShortCode();    
    	String rightCardString = cardRight.getSymbol().getShortCode() + cardRight.getValue().getShortCode();     	
    	
    	return leftCardString + "," + rightCardString;
    }
    
	public CalculatedHand getCalculatedHand()
	{
		return calculatedHand;
	}

	public void setCalculatedHand(CalculatedHand calculatedHand)
	{
		this.calculatedHand = calculatedHand;
	}

	@Override
	public String toString()
	{
		return "Player [name=" + name + ", cardLeft=" + cardLeft + ", cardRight=" + cardRight + ", calculatedHand=" + calculatedHand + "]";
	}
}