package com.github.eventasia.eventstore.command;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class ChildCustomCommand extends FirstCustomCommand {
    public ChildCustomCommand(String name, CountDownLatch countDownLatch) {
        super(name, countDownLatch);
    }

    @Override
    public UUID getAggregateId() {
        return super.getAggregateId();
    }
}