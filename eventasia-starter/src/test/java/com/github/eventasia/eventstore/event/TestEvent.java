package com.github.eventasia.eventstore.event;

import com.github.eventasia.framework.Event;

public class TestEvent implements Event {

    private String message;

    public TestEvent() {
    }

    public TestEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
