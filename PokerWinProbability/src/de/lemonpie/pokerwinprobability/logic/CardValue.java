package de.lemonpie.pokerwinprobability.logic;

public enum CardValue 
{
	BACK("B", "B", 0),
    TWO("2", "2", 2),
    THREE("3", "3", 3),
    FOUR("4", "4", 4),
    FIVE("5", "5", 5),
    SIX("6", "6", 6),
    SEVEN("7", "7", 7),
    EIGHT("8", "8", 8),
    NINE("9", "9", 9),
    TEN("10", "X", 10),
    JACK("J", "J", 11),
    QUEEN("D", "D", 12),
    KING("K", "K", 13),
    ACE("A", "A", 14);
    
    private String shortName;
    private String shortCode;
    private Integer value;
    
    private CardValue(String shortName, String shortCode, Integer value)
    {
        this.shortName = shortName;
        this.shortCode = shortCode;
        this.value = value;
    }

    public String getShortName() 
    {
        return shortName;
    }

	public String getShortCode()
	{
		return shortCode;
	}	
	
	public Integer getValue()
	{
		return value;
	}
	
	public static CardValue fromValue(int value)
	{
		for(CardValue currentValue : values())
		{
			if(currentValue.value == value)
			{
				return currentValue;
			}
		}
		
		return null;
	}

	public static CardValue fromShortCode(String shortCode)
	{
		for(CardValue value : values())
		{
			if(value.shortCode.equals(shortCode.toUpperCase()))
			{
				return value;
			}
		}
		
		return null;
	}
}