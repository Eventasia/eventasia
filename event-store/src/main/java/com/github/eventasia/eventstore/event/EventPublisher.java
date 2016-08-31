package com.github.eventasia.eventstore.event;

import com.github.eventasia.framework.Event;
import org.springframework.messaging.Message;

public interface EventPublisher {

    void publishEvent(Message<Event> event);
}
