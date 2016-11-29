package com.github.eventasia;

import java.io.Serializable;
import java.util.UUID;

public interface Command extends Serializable {
    UUID getAggregateId();
}
