package com.github.eventasia.eventstore.wrapper;

import com.github.eventasia.eventstore.command.AggregateCommandHandler;
import com.github.eventasia.eventstore.command.ChildCustomCommand;
import com.github.eventasia.eventstore.command.FirstCustomCommand;
import com.github.eventasia.eventstore.event.TestEvent;
import com.github.eventasia.framework.Event;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class TestEventListenerWrapper {


    private Log log = LogFactory.getLog(TestEventListenerWrapper.class);

    @AggregateCommandHandler
    public Event onApplicationEvent(FirstCustomCommand command) {
        command.getCountDownLatch().countDown();
        command.incrementCallCount();
        log.debug("m=onApplicationEvent, message='" + command.getMessage() + "'");

        return new TestEvent("TEST EVENT");
    }

    @AggregateCommandHandler
    public void onApplicationEvent(ChildCustomCommand command) {
        command.getCountDownLatch().countDown();
        command.incrementCallCount();
        log.debug("m=onApplicationEvent, message='" + command.getMessage() + "'");
    }
}
