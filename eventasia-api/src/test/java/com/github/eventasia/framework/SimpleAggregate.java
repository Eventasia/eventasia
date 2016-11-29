package com.github.eventasia.framework;

import com.github.eventasia.Aggregate;

import java.util.UUID;

public class SimpleAggregate implements Aggregate<SimpleAggregate> {
    private UUID userId;
    private volatile long version;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public UUID getAggregateId() {
        return userId;
    }

    @Override
    public synchronized Long getVersion() {
        return version;
    }

    @Override
    public synchronized Long incrementVersion() {
        return version++;
    }
}
