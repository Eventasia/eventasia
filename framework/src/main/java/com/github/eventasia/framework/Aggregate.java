package com.github.eventasia.framework;

import java.io.Serializable;
import java.util.UUID;

public interface Aggregate<T extends Serializable> {

    UUID getAggregateId();

    long getVersion();

    long incrementVersion();

}
