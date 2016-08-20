package com.github.eventasia.eventstore.repository;

import com.github.eventasia.framework.Aggregate;

public interface ReadWriteAggregateRepository<A extends Aggregate> extends AggregateRepository<A> {

    void save(A aggregate);

}
