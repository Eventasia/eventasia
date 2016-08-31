package com.github.eventasia.eventstore.event;

import com.github.eventasia.framework.Event;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(EventPublisher.class)
public class EventPublisherImpl implements EventPublisher {

    private Log log = LogFactory.getLog(EventPublisherImpl.class);

    private final ApplicationEventPublisher publisher;

    @Autowired
    public EventPublisherImpl(ApplicationEventPublisher publisher){
        this.publisher = publisher;
    }

    @Override
    public void publishEvent(Message<Event> event) {
        publisher.publishEvent(event);
    }
}
