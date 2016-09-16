package com.github.eventasia.eventstore.command;

import com.github.eventasia.framework.command.Command;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class FirstCustomCommand implements Command {
    private final CountDownLatch countDownLatch;
    private String message;
    private volatile int callCount = 0;
    private UUID aggregateId;

    public FirstCustomCommand(String message, CountDownLatch countDownLatch) {
        this.aggregateId = UUID.randomUUID();
        this.message = message;
        this.countDownLatch = countDownLatch;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public synchronized int getCallCount() {
        return callCount;
    }

    public synchronized void setCallCount(int callCount) {
        this.callCount = callCount;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public synchronized void incrementCallCount() {
        callCount++;
    }

    @Override
    public UUID getAggregateId() {
        return aggregateId;
    }
}