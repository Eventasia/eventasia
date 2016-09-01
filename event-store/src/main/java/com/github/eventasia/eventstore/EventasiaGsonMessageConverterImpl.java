package com.github.eventasia.eventstore;

import com.github.eventasia.eventstore.event.EventasiaMessage;
import com.github.eventasia.eventstore.event.EventasiaMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class EventasiaGsonMessageConverterImpl implements EventasiaMessageConverter {

    public byte[] serialize(EventasiaMessage message) {
        return null;
    }

    public EventasiaMessage deserialize(byte[] message) {
        return null;
    }
}
