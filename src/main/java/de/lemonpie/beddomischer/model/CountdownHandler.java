package de.lemonpie.beddomischer.model;

public class CountdownHandler
{
	private static Countdown instance;

	public static Countdown getInstance()
	{
		if(instance == null)
		{
			instance = new Countdown();
		}
		return instance;
	}
}
