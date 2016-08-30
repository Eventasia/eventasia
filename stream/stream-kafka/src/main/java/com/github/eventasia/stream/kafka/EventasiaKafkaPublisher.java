package com.github.eventasia.stream.kafka;


import com.github.eventasia.framework.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventasiaKafkaPublisher {

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @EventListener
    public void listen(Event event) {
        kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), event.toString());
    }
}
