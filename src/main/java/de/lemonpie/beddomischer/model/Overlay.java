package de.lemonpie.beddomischer.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import de.lemonpie.beddomischer.listener.OverlayListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@DatabaseTable(tableName = "Overlay")
public class Overlay
{
	private transient List<OverlayListener> listeners;

	@DatabaseField(unique = true, generatedId = true)
	private int id;

	@DatabaseField
	private boolean hidePlayer;
	@DatabaseField
	private boolean hideBoard;

	public Overlay()
	{
		this(false, false);
	}

	public Overlay(boolean hidePlayer, boolean hideBoard)
	{
		this.hidePlayer = hidePlayer;
		this.hideBoard = hideBoard;
		this.listeners = new ArrayList<>();
	}

	public boolean isHidePlayer()
	{
		return hidePlayer;
	}

	public void setHidePlayer(boolean hidePlayer)
	{
		boolean newValue = this.hidePlayer != hidePlayer;
		this.hidePlayer = hidePlayer;

		if(newValue)
		{
			fireListener(listener -> listener.hidePlayerDidChange(this));
		}
	}

	public boolean isHideBoard()
	{
		return hideBoard;
	}

	public void setHideBoard(boolean hideBoard)
	{
		boolean newValue = this.hideBoard != hideBoard;
		this.hideBoard = hideBoard;

		if(newValue)
		{
			fireListener(listener -> listener.hideBoardDidChange(this));
		}
	}

	public void addListener(OverlayListener overlayListener)
	{
		this.listeners.add(overlayListener);
	}

	public void removeListener(OverlayListener overlayListener)
	{
		this.listeners.remove(overlayListener);
	}

	private void fireListener(Consumer<OverlayListener> consumer)
	{
		for(OverlayListener overlayListener : listeners)
		{
			consumer.accept(overlayListener);
		}
	}
}
