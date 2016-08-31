package com.github.eventasia.eventstore.service;

import com.github.eventasia.framework.Aggregate;
import com.github.eventasia.framework.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class AggregateService<A extends Aggregate, C extends Command> {

    private final ApplicationEventPublisher publisher;

    @Autowired
    public AggregateService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void process(C command) {
        publisher.publishEvent(command);
    }
}