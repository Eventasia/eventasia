package com.github.eventasia.framework;

import com.github.eventasia.Eventasia;
import com.github.eventasia.EventasiaBuilder;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class EventasiaBasicTest {
    @Test
    public void testIt() {

        Eventasia eventasia = new EventasiaBuilder().create();

        Publisher<SimpleCommand> simpleCommandPublisher =
                eventasia.registerCommandHandler(SimpleAggregate.class, SimpleCommand.class);


        simpleCommandPublisher.subscribe(new Subscriber<SimpleCommand>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(SimpleCommand simpleEvent) {
                //Initially represented as eventasia.sendEvent, but will certainly change
                eventasia.sendEvent(SimpleAggregate.class, new SimpleEvent());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
