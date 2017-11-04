package de.lemonpie.beddomischer.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.winprobability.CalculatedHand;
import de.lemonpie.beddomischer.storage.CardType;
import de.lemonpie.beddomischer.validator.CardValidator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@DatabaseTable(tableName = "Player")
public class Player {

	private List<PlayerListener> listeners;

	@DatabaseField(unique = true, generatedId = true)
	private int id;
	@DatabaseField
	private String name;
	@DatabaseField
	private String twitchName;
	@DatabaseField
	private PlayerState state;

	@DatabaseField(persisterClass = CardType.class)
	private Card cardLeft = Card.EMPTY;
	@DatabaseField(persisterClass = CardType.class)
	private Card cardRight = Card.EMPTY;

	@DatabaseField
	private int chips;

	private CalculatedHand calculatedHand;
	private double winprobability;

	public Player() {
		this(0);
	}

	public Player(int id) {
		listeners = new LinkedList<>();

		this.id = id;
		this.name = "[Player]";
		this.twitchName = "[TwitchName]";
		this.state = PlayerState.ACTIVE;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		fireListener(listener -> listener.nameDidChange(this, name));
	}

	public String getTwitchName() {
		return twitchName;
	}

	public void setTwitchName(String twitchName) {
		this.twitchName = twitchName;
		fireListener(listener -> listener.twitchNameDidChange(this, twitchName));
	}

	public PlayerState getPlayerState() {
		return state;
	}

	public void setPlayerState(PlayerState state) {
		this.state = state;
		fireListener(listener -> listener.stateDidChange(this, state));
	}

	public Card getCardLeft() {
		return cardLeft;
	}

	public void setCardLeft(Card cardLeft) {
		if (CardValidator.getInstance().validateCard(cardLeft)) {
			this.cardLeft = cardLeft;
			fireListener(listener -> listener.cardDidChangeAtIndex(this, 0, cardLeft));
		}
	}

	public Card getCardRight() {
		return cardRight;
	}

	public void setCardRight(Card cardRight) {
		if (CardValidator.getInstance().validateCard(cardRight)) {
			this.cardRight = cardRight;
			fireListener(listener -> listener.cardDidChangeAtIndex(this, 1, cardRight));
		}
	}

	public int getChips() {
		return chips;
	}

	public void setChips(int chips) {
		this.chips = chips;
		fireListener(listener -> listener.chipsDidChange(this, chips));
	}

	public double getWinprobability() {
		return winprobability;
	}

	public void setWinprobability(double winprobability) {
		this.winprobability = winprobability;
		fireListener(listener -> listener.winProbabilityDidChange(this, winprobability));
	}

	public CalculatedHand getCalculatedHand() {
		return calculatedHand;
	}

	public void setCalculatedHand(CalculatedHand calculatedHand) {
		this.calculatedHand = calculatedHand;
	}

	public void addListener(PlayerListener playerListener) {
		this.listeners.add(playerListener);
	}

	public void removeListener(PlayerListener playerListener) {
		this.listeners.remove(playerListener);
	}

	private void fireListener(Consumer<PlayerListener> consumer) {
		for (PlayerListener playerListener : listeners) {
			consumer.accept(playerListener);
		}
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("twitchName", twitchName);
		map.put("state", state);

		map.put("card1", cardLeft != null ? cardLeft.getName() : "back");
		map.put("card2", cardRight != null ? cardRight.getName() : "back");

		map.put("chips", chips);
		map.put("winprobability", winprobability);
		return map;
	}

	public void setCard(int index, Card card) {
		if (index == 0) {
			setCardLeft(card);
		} else if (index == 1) {
			setCardRight(card);
		} else {
			throw new IllegalArgumentException("Index is " + index + " should be 0 or 1");
		}
	}

	public void clearCards() {
		setCard(0, Card.EMPTY);
		setCard(1, Card.EMPTY);
	}

	public void setCard(Card card) {
		if (cardLeft == null || cardLeft == Card.EMPTY) {
            setCardLeft(card);
        } else {
            setCardRight(card);
        }
	}
}
