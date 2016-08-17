package com.github.eventasia.eventstore.stream;

import com.github.eventasia.framework.Event;

public interface EventStream<E extends Event> {

    void postEvent(E event);

}
