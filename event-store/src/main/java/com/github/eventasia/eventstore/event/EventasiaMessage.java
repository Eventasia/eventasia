package com.github.eventasia.eventstore.event;

import com.github.eventasia.framework.Event;

import java.io.Serializable;

public class EventasiaMessage implements Serializable {

    private Event event;

    public EventasiaMessage() {

    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public EventasiaMessage(Event event) {
        this.event = event;
    }
}
