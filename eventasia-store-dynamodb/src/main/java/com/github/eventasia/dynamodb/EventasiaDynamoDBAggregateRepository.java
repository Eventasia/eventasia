package com.github.eventasia.dynamodb;

import com.github.eventasia.eventstore.repository.ReadWriteAggregateRepository;
import com.github.eventasia.framework.Aggregate;

import java.util.UUID;

public interface EventasiaDynamoDBAggregateRepository<A extends Aggregate> extends ReadWriteAggregateRepository<A> {
    A get(Class clazz, UUID id);
}
