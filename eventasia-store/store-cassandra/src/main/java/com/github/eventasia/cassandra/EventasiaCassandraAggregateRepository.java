package com.github.eventasia.cassandra;

import com.github.eventasia.eventstore.repository.ReadWriteAggregateRepository;
import com.github.eventasia.framework.Aggregate;

public interface EventasiaCassandraAggregateRepository<A extends Aggregate> extends ReadWriteAggregateRepository<A>{

    void addMapper(Class<A> c);

    void addUDT(Class c);

    void addCodec(Class c);
}
