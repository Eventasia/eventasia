package com.github.eventasia.eventstore.stream;

import com.github.eventasia.framework.Event;
import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
public class ObservableEventStreamImpl<E extends Event> extends Observable implements EventStream<E> {

    public void postEvent(E event) {
        setChanged();
        notifyObservers(event);
    }

}
