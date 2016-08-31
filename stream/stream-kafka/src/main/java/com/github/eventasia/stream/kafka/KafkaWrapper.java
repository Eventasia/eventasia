package com.github.eventasia.stream.kafka;

import com.github.eventasia.framework.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaWrapper {

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public void publishEvent(Message<Event> event) {
        kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), event.getPayload().toString());
    }
}
