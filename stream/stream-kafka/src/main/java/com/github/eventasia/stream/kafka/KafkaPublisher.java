package com.github.eventasia.stream.kafka;

import com.github.eventasia.eventstore.event.EventPublisher;
import com.github.eventasia.framework.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher implements EventPublisher {

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @EventListener
    @Override
    public void publishEvent(Message<Event> event) {
        kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), event.getPayload().toString());
    }
}
