package com.github.eventasia.eventstore;

import com.github.eventasia.eventstore.command.ChildCustomCommand;
import com.github.eventasia.eventstore.command.FirstCustomCommand;
import com.github.eventasia.eventstore.wrapper.TestEventListenerWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AggregateServiceTest.class, AggregateService.class, TestEventListenerWrapper.class})
@Configuration
public class AggregateServiceTest {
    @Autowired
    TestEventListenerWrapper wrapper;

    @Autowired
    AggregateService<?, FirstCustomCommand> aggregate;

    @Test
    public void processSingleMethodEvent() {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        FirstCustomCommand first = new FirstCustomCommand("FIRST", countDownLatch);

        aggregate.process(first);
        try {
            countDownLatch.await(5l, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Assert.fail("Command not processed");
            return;
        }
        Assert.assertEquals(1, first.getCallCount());
    }

    @Test
    public void processTwoMethodsEvent() {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        ChildCustomCommand second = new ChildCustomCommand("SECOND", countDownLatch);

        aggregate.process(second);
        try {
            countDownLatch.await(5l, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Assert.fail("Command not processed");
            return;
        }
        Assert.assertEquals(2, second.getCallCount());
    }


}