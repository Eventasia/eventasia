package com.github.eventasia.eventstore.repository;

import com.github.eventasia.framework.Aggregate;

import java.util.UUID;

public interface AggregateRepository<A extends Aggregate> {

    A get(UUID uuid);

}
