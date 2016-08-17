package com.github.eventasia.eventstore.command;

import org.springframework.context.event.EventListener;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EventListener
public @interface AggregateCommandHandler {

}
