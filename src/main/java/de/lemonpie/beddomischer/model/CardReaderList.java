package de.lemonpie.beddomischer.model;

import de.lemonpie.beddomischer.listener.CardReaderListListener;
import de.lemonpie.beddomischer.model.reader.CardReader;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CardReaderList implements Iterable<CardReader> {

    private List<CardReader> data = new ArrayList<>();
    private List<CardReaderListListener> listeners;

    public CardReaderList() {
        this.listeners = new LinkedList<>();
    }

    public boolean add(CardReader reader) {
        fireListener(l -> l.addCardReader(reader));
        return data.add(reader);
    }


    public boolean addAll(List<? extends CardReader> players) {
        for (CardReader reader : players) {
            fireListener(l -> l.addCardReader(reader));
        }
        return data.addAll(players);
    }

    public Optional<CardReader> getCardReader(int id) {
        return data.stream().filter(r -> r.getReaderId() == id).findFirst();
    }

    public Stream<CardReader> stream() {
        return data.stream();
    }

    public void clear() {
        for (CardReader reader : data) {
            fireListener(l -> l.removeCardReader(reader));
        }
        data.clear();
    }

    public boolean remove(Object o) {
        if (o instanceof CardReader) {
            fireListener(l -> l.removeCardReader((CardReader) o));
        }
        return data.remove(o);
    }

    public void addListener(CardReaderListListener cardReaderListListener) {
        this.listeners.add(cardReaderListListener);
    }

    public void removeListener(CardReaderListListener cardReaderListListener) {
        this.listeners.remove(cardReaderListListener);
    }

    private void fireListener(Consumer<CardReaderListListener> consumer) {
        for (CardReaderListListener playerListener : listeners) {
            consumer.accept(playerListener);
        }
    }

    @Override
    public Iterator<CardReader> iterator() {
        return data.iterator();
    }

    @Override
    public void forEach(Consumer<? super CardReader> action) {
        data.forEach(action);
    }

    @Override
    public Spliterator<CardReader> spliterator() {
        return data.spliterator();
    }

}
