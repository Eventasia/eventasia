package com.github.eventasia.framework.util;

import com.github.eventasia.framework.Event;

import java.util.Arrays;
import java.util.List;

public class Events {

    public static <T extends Event> List<T> asList(T... events) {
        return Arrays.asList(events);
    }
}
