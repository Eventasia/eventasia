package com.github.eventasia;

import org.reactivestreams.Publisher;

public class Eventasia {
    protected Eventasia() {

    }

    public <C extends Command> Publisher<C> registerCommandHandler(
            Class<? extends Aggregate> aggregateClass,
            Class<C> commandClass) {
        return s -> {

        };
    }

    public void sendEvent(Class<? extends Aggregate> aggregateClass, Event simpleEvent) {

    }
}
