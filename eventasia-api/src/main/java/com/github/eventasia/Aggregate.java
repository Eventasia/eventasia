package com.github.eventasia;

import java.io.Serializable;
import java.util.UUID;

public interface Aggregate<T extends Serializable> extends Serializable {

    UUID getAggregateId();

    Long getVersion();

    Long incrementVersion();
}