package de.lemonpie.beddomischer.model;

import de.lemonpie.beddomischer.listener.PlayerListener;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.winprobability.CalculatedHand;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Player {

	private List<PlayerListener> listeners;

	private final int id;
	private String name;
	private String twitchName;

	private Card cardLeft;
	private Card cardRight;

	private int chips;
	private CalculatedHand calculatedHand;

	public Player(int id) {
		listeners = new LinkedList<>();

		this.id = id;
		this.name = "[Player]";
		this.twitchName = "[TwitchName]";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		fireListener(listener -> listener.nameDidChange(name));
	}

	public String getTwitchName() {
		return twitchName;
	}

	public void setTwitchName(String twitchName) {
		this.twitchName = twitchName;
		fireListener(listener -> listener.twitchNameDidChange(twitchName));
	}

	public Card getCardLeft() {
		return cardLeft;
	}

	public void setCardLeft(Card cardLeft) {
		this.cardLeft = cardLeft;
		fireListener(listener -> listener.cardDidChangeAtIndex(0, cardLeft));
	}

	public Card getCardRight() {
		return cardRight;
	}

	public void setCardRight(Card cardRight) {
		this.cardRight = cardRight;
		fireListener(listener -> listener.cardDidChangeAtIndex(1, cardRight));
	}

	public int getChips() {
		return chips;
	}

	public void setChips(int chips) {
		this.chips = chips;
		fireListener(listener -> listener.chipsDidChange(chips));
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
		map.put("name", name);
		map.put("twitchName", twitchName);
		map.put("cardLeft", cardLeft != null ? cardLeft.getName() : "back");
		map.put("cardRight", cardRight != null ? cardRight.getName() : "back");
		return map;
	}
}
