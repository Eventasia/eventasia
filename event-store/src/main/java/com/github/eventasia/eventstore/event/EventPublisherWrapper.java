package com.github.eventasia.eventstore.event;

import com.github.eventasia.framework.Event;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherWrapper {

    private Log log = LogFactory.getLog(EventPublisherWrapper.class);

    private ApplicationEventPublisher publisher;

    public EventPublisherWrapper(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishEvent(Message<Event> event) {
        log.info("Wrapper m=publish, event='" + event.getPayload().toString() + "'");
        publisher.publishEvent(event);
    }
}
