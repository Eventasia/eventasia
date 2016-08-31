package com.github.eventasia.eventstore.event;

import com.github.eventasia.framework.Event;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;

public interface EventPublisher {

    @EventListener
    void publishEvent(Message<Event> event);
}
