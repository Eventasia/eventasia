package com.github.eventasia.eventstore.event;

import com.github.eventasia.framework.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class EventasiaGsonMessageConverterImpl implements EventasiaMessageConverter {

    private final Gson gson;

    public EventasiaGsonMessageConverterImpl() {
        this(null);
    }

    public EventasiaGsonMessageConverterImpl(Gson gson) {
        if(gson==null)
            this.gson = new GsonBuilder().registerTypeAdapter(Event.class, new EventasiaMessageAdapter()).create();
        else
            this.gson = gson;
    }

    public byte[] serialize(EventasiaMessage message) {

        return gson.toJson(message, EventasiaMessage.class).getBytes();
    }

    public EventasiaMessage deserialize(byte[] message) {

        return gson.fromJson(new String(message), EventasiaMessage.class);
    }
}
