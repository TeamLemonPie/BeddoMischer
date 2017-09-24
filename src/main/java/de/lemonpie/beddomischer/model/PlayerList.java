package de.lemonpie.beddomischer.model;

import de.lemonpie.beddomischer.listener.PlayerListListener;

import java.util.*;
import java.util.function.Consumer;

public class PlayerList implements Iterable<Player> {

    private List<Player> data = new ArrayList<>();
    private List<PlayerListListener> listeners;

    public PlayerList() {
        this.listeners = new LinkedList<>();
    }

    public Player add() {
        Player player = new Player();
        fireListener(l -> l.addPlayer(player));
        return data.add(player) == true ? player : null;
    }

    public boolean addAll(List<Player> players) {
        for (Player player : players) {
            fireListener(l -> l.addPlayer(player));
        }
        return data.addAll(players);
    }

    public Optional<Player> getPlayer(int id) {
        return data.stream().filter(r -> r.getId() == id).findFirst();
    }

    public void clear() {
        for (Player player : data) {
            fireListener(l -> l.removePlayer(player));
        }
        data.clear();
    }

    public boolean remove(Object o) {
        if (o instanceof Player) {
            fireListener(l -> l.removePlayer((Player) o));
        }
        return data.remove(o);
    }

    public void addListener(PlayerListListener playerListener) {
        this.listeners.add(playerListener);
    }

    public void removeListener(PlayerListListener playerListener) {
        this.listeners.remove(playerListener);
    }

    private void fireListener(Consumer<PlayerListListener> consumer) {
        for (PlayerListListener playerListener : listeners) {
            consumer.accept(playerListener);
        }
    }

    @Override
    public Iterator<Player> iterator() {
        return data.iterator();
    }

    @Override
    public void forEach(Consumer<? super Player> action) {
        data.forEach(action);
    }

    @Override
    public Spliterator<Player> spliterator() {
        return data.spliterator();
    }

}
