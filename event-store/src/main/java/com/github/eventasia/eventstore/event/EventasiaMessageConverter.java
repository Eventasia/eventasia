package com.github.eventasia.eventstore.event;

public interface EventasiaMessageConverter {

    public byte[] serialize(EventasiaMessage message);

    public EventasiaMessage deserialize(byte[] message);
}
