package com.github.eventasia.stream.kafka;

import com.github.eventasia.eventstore.event.EventPublisher;
import com.github.eventasia.framework.Event;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaWrapper implements EventPublisher{

    private Log log = LogFactory.getLog(KafkaWrapper.class);

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    private final ApplicationEventPublisher publisher;

    public KafkaWrapper(ApplicationEventPublisher publisher){
        this.publisher = publisher;
    }

    @Override
    public void publishEvent(Message<Event> event) {
        log.info("KafkaWrapper m=publish, event='" + event.getPayload().toString() + "'");
        kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), event.getPayload().toString());
    }

//    @KafkaListener( topics = "booking")
//    public void listen(Message<Event> event) {
//        log.debug("KafkaListener.received="+ event.getPayload().toString());
//        publisher.publishEvent(event.getPayload());
//        log.debug("KafkaListener.publishToApplicationEventPublisher="+ event.getPayload().toString());
//    }
}
