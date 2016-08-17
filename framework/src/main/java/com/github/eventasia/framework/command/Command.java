package com.github.eventasia.framework.command;

import java.io.Serializable;
import java.util.UUID;

public interface Command extends Serializable {
    UUID getAggregateId();
}