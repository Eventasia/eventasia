package com.github.eventasia.stream.kafka;

import com.github.eventasia.eventstore.event.EventPublisher;
import com.github.eventasia.eventstore.event.EventStoreListener;
import com.github.eventasia.eventstore.event.EventasiaGsonMessageConverterImpl;
import com.github.eventasia.eventstore.event.EventasiaMessage;
import com.google.gson.JsonParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisherWrapper implements EventPublisher{

    private Log log = LogFactory.getLog(KafkaEventPublisherWrapper.class);

    private EventasiaGsonMessageConverterImpl messageConverter = new EventasiaGsonMessageConverterImpl();

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    private final ApplicationEventPublisher publisher;

    public KafkaEventPublisherWrapper(ApplicationEventPublisher publisher){
        this.publisher = publisher;
    }

    // TODO throw exception on startup if kafka offline
    @Override
    public void publishEvent(EventasiaMessage eventMessage) {
        log.info("KafkaWrapper m=publish, event=" + eventMessage.toString() );
        kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), new String(messageConverter.serialize(eventMessage) ));
    }

    @KafkaListener(topicPattern = ".*eventasia")
    @Override
    public void receiveAndPropagateEvent(String eventMessage) {
        try {
            log.info("KafkaListener.receive: "+eventMessage);
            EventasiaMessage eventasiaMessage = messageConverter.deserialize(eventMessage.getBytes());
            publisher.publishEvent(eventasiaMessage.getEvent());
            log.debug("KafkaListener.propagate="+ eventasiaMessage.getEvent());
        } catch (JsonParseException jsonParseException){
            log.info("Unable to convert message. You probably does not have the Event used in this message");
        }
    }
}
