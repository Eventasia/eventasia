package com.github.eventasia.eventstore.repository;

import com.github.eventasia.cassandra.CassandraConfig;
import com.github.eventasia.framework.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventasiaCassandraAggregateRepositoryImpl<A extends Aggregate> implements ReadWriteAggregateRepository<A> {

    @Autowired
    CassandraConfig cassandraConfig;

    @Override
    public A get(UUID uuid) {

        return (A) cassandraConfig.getManager().mapper(Aggregate.class).get();
    }

    @Override
    public void save(A aggregate) {

        cassandraConfig.getManager().mapper(Aggregate.class).save(aggregate);
    }
}
