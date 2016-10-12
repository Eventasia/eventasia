package com.github.eventasia.eventstore.event;

import com.google.gson.JsonParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(EventPublisher.class)
public class EventPublisherWrapper implements EventPublisher{

    private Log log = LogFactory.getLog(EventPublisherWrapper.class);

    private EventasiaGsonMessageConverterImpl messageConverter = new EventasiaGsonMessageConverterImpl();

    private ApplicationEventPublisher publisher;

    public EventPublisherWrapper(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishEvent(EventasiaMessage eventMessage) {
        log.info("Wrapper m=publish, event='" + eventMessage.getEvent().toString() + "'");
        publisher.publishEvent(eventMessage.getEvent());
    }

    @Override
    public void receiveAndPropagateEvent(String eventMessage) {
        try {
            EventasiaMessage eventasiaMessage = messageConverter.deserialize(eventMessage.getBytes());
            publisher.publishEvent(eventasiaMessage.getEvent());
        } catch (JsonParseException jsonParseException){
            log.info("Unable to convert message. You probably does not have the Event used in this message");
        }
    }
}
