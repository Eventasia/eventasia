package com.github.eventasia.eventstore.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(EventPublisher.class)
public class EventPublisherWrapper implements EventPublisher{

    private Log log = LogFactory.getLog(EventPublisherWrapper.class);

    private ApplicationEventPublisher publisher;

    public EventPublisherWrapper(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishEvent(EventasiaMessage eventMessage) {
        log.info("Wrapper m=publish, event='" + eventMessage.getEvent().toString() + "'");
        publisher.publishEvent(eventMessage.getEvent());
    }
}
