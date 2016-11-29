package com.github.eventasia.framework;

import com.github.eventasia.Command;

import java.util.UUID;

public class SimpleCommand implements Command {
    private UUID commandId;

    public SimpleCommand(UUID commandId) {
        this.commandId = commandId;
    }

    @Override
    public UUID getAggregateId() {
        return commandId;
    }
}
