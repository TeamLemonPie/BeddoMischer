package de.lemonpie.beddomischer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.listener.BoardListener;
import de.lemonpie.beddomischer.storage.type.ByteSetType;
import de.lemonpie.beddomischer.storage.type.CardArrayType;
import de.lemonpie.beddomischer.validator.CardValidator;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

@DatabaseTable(tableName = "Board")
public class Board
{
	private transient List<BoardListener> listeners;

	@DatabaseField(unique = true, generatedId = true)
	private int id;

	@DatabaseField(persisterClass = CardArrayType.class)
	private Card[] cards;

	@DatabaseField(persisterClass = ByteSetType.class)
	private Set<Byte> readerIds;

	@DatabaseField
	private int smallBlind;
	@DatabaseField
	private int bigBlind;
	@DatabaseField
	private int ante;

	public Board()
	{
		listeners = new LinkedList<>();
		cards = new Card[5];
		readerIds = new HashSet<>();

		Arrays.fill(cards, Card.EMPTY);
	}

	public Card getCard(int index) throws IndexOutOfBoundsException
	{
		if(index < 0 || index >= cards.length)
		{
			throw new IndexOutOfBoundsException("Index: " + index + " size: " + cards.length);
		}
		return cards[index];
	}

	public void setCard(int index, Card card) throws IndexOutOfBoundsException
	{
		if(index < 0 || index >= cards.length)
		{
			throw new IndexOutOfBoundsException("Index: " + index + " size: " + cards.length);
		}

		if(BeddoMischerMain.getBlockOption() != BlockOption.NONE)
		{
			return;
		}

		if(CardValidator.getInstance().validateCard(card))
		{
			cards[index] = card;
			fireListener(listener -> listener.cardDidChangeAtIndex(index, card));
		}
	}

	public void setCard(Card card) throws IndexOutOfBoundsException
	{
		for(int i = 0; i < cards.length; i++)
		{
			if(cards[i] == Card.EMPTY)
			{
				setCard(i, card);
				break;
			}
		}
	}

	public int getSmallBlind()
	{
		return smallBlind;
	}

	public void setSmallBlind(int smallBlind)
	{
		this.smallBlind = smallBlind;
		fireListener(listener -> listener.smallBlindDidChange(smallBlind));
	}

	public int getBigBlind()
	{
		return bigBlind;
	}

	public void setBigBlind(int bigBlind)
	{
		this.bigBlind = bigBlind;
		fireListener(listener -> listener.bigBlindDidChange(bigBlind));
	}

	public int getAnte()
	{
		return ante;
	}

	public void setAnte(int ante)
	{
		this.ante = ante;
		fireListener(listener -> listener.anteDidChange(ante));
	}

	public Set<Byte> getReaderIds()
	{
		return readerIds;
	}

	public boolean isBoardReader(byte readerId)
	{
		return readerIds.contains(readerId);
	}

	public void addReaderId(byte readerId)
	{
		readerIds.add(readerId);
		fireListener(BoardListener::readerListDidChange);
	}

	public void removeReaderId(byte readerId)
	{
		readerIds.remove(readerId);
		fireListener(BoardListener::readerListDidChange);
	}

	public void addListener(BoardListener boardListener)
	{
		this.listeners.add(boardListener);
	}

	public void removeListener(BoardListener boardListener)
	{
		this.listeners.remove(boardListener);
	}

	private void fireListener(Consumer<BoardListener> consumer)
	{
		for(BoardListener boardListener : listeners)
		{
			consumer.accept(boardListener);
		}
	}

	public Card[] getCards()
	{
		return cards;
	}

	@JsonIgnore
	public int getNumberOfMissingCards()
	{
		return (int) Stream.of(cards).filter(card -> card.equals(Card.EMPTY)).count();
	}

	public void clearCards()
	{
		for(int i = 0; i < getCards().length; i++)
		{
			setCard(i, Card.EMPTY);
		}
	}

	@Override
	public String toString()
	{
		return "Board[cards=" + Arrays.toString(cards) + ']';
	}
}