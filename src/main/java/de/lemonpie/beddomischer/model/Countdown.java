package de.lemonpie.beddomischer.model;

import de.lemonpie.beddomischer.listener.CountdownListener;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Countdown
{
	private long pauseEndTime;
	private long pauseStartTime;
	private List<CountdownListener> countdownListeners;

	public Countdown()
	{
		countdownListeners = new LinkedList<>();
	}

	public long getPauseEndTime()
	{
		return pauseEndTime;
	}

	public void setPauseEndTime(long pauseEndTime)
	{
		this.pauseEndTime = pauseEndTime;
		fireListener(l -> l.pauseCountdownDidChange(pauseEndTime));
	}

	public long getPauseStartTime()
	{
		return pauseStartTime;
	}

	public void setPauseStartTime(long pauseStartTime)
	{
		this.pauseStartTime = pauseStartTime;
		fireListener(l -> l.gameCountdownDidChange(pauseStartTime));
	}

	public void addCountdownListener(CountdownListener countdownListener)
	{
		countdownListeners.add(countdownListener);
	}

	private void fireListener(Consumer<CountdownListener> consumer)
	{
		for(CountdownListener countdownListener : countdownListeners)
		{
			consumer.accept(countdownListener);
		}
	}
}
