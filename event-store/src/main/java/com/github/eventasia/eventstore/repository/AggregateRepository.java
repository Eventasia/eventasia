package com.github.eventasia.eventstore.repository;

import com.github.eventasia.framework.Aggregate;

import java.util.UUID;

public interface AggregateRepository<A extends Aggregate> {

    public void save(A aggregate);

    public A get(UUID uuid);

}
