package com.github.eventasia.eventstore.aspect;

import com.github.eventasia.eventstore.AggregateService;
import com.github.eventasia.eventstore.command.FirstCustomCommand;
import com.github.eventasia.eventstore.wrapper.TestEventListenerWrapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        PropagateCommandReturnedEventsAspectTest.class,
        AggregateService.class,
        PropagateCommandReturnedEventsAspect.class,
        TestEventListenerWrapper.class})

@Configuration
@EnableAspectJAutoProxy
public class PropagateCommandReturnedEventsAspectTest {

    @Autowired
    TestEventListenerWrapper wrapper;

    @Autowired
    AggregateService<?, FirstCustomCommand> aggregate;

    @Autowired
    private ApplicationEventPublisher publisher;


    @Ignore
    @Test
    public void testProcess() {
        aggregate.process(new FirstCustomCommand("ITS ME, MARIO! AGAIN!", new CountDownLatch(1)));
    }

}