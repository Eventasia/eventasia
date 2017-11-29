package com.github.eventasia.dynamodb;

import com.github.eventasia.eventstore.repository.ReadWriteAggregateRepository;
import com.github.eventasia.framework.Aggregate;

public interface EventasiaDynamoDBAggregateRepository<A extends Aggregate> extends ReadWriteAggregateRepository<A> {

}
