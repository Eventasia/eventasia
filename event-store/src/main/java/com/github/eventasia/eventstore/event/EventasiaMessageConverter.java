package com.github.eventasia.eventstore.event;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class EventasiaMessageConverter {

    final static Gson gson = new Gson();


    public static String serialize(EventasiaMessage message) {

        Type type = new TypeToken<EventasiaMessage>() {}.getType();
        return gson.toJson(message, type);
    }

    public static EventasiaMessage deserialize(String message) {

        return gson.fromJson(message, EventasiaMessage.class);
    }
}
