package de.lemonpie.beddomischer.listener;

public interface CountdownListener
{
	void pauseCountdownDidChange(long value);

	void gameCountdownDidChange(long value);
}
