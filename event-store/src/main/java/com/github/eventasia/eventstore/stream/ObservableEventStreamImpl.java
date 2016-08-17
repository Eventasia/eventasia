package com.github.eventasia.eventstore.stream;

import com.github.eventasia.framework.Event;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
@ConditionalOnMissingBean
public class ObservableEventStreamImpl<E extends Event> extends Observable implements EventStream<E> {

    public void postEvent(E event) {
        setChanged();
        notifyObservers(event);
    }

}
