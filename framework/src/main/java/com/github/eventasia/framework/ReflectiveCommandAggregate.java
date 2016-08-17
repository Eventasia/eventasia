package com.github.eventasia.framework;

import com.github.eventasia.framework.command.Command;

import java.io.Serializable;

public interface ReflectiveCommandAggregate<A extends ReflectiveCommandAggregate, C extends Command> extends Serializable, Aggregate<A> {
}
