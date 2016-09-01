package com.github.eventasia.cassandra;

import com.github.eventasia.framework.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventasiaCassandraAggregateRepositoryImpl<A extends Aggregate> implements EventasiaCassandraAggregateRepository<A>{

    @Autowired
    private CassandraConfig cassandraConfig;

    @Override
    public A get(UUID uuid) {

        return (A) cassandraConfig.getMapper().get(uuid);
    }

    @Override
    public void save(A aggregate) {
        aggregate.incrementVersion();
        cassandraConfig.getMapper().save(aggregate);
    }

    @Override
    public void addMapper(Class<A> c) {
        cassandraConfig.addMapper(c);
    }

    @Override
    public void addUDT(Class c) {
        cassandraConfig.addUDT(c);
    }

    @Override
    public void addCodec(Class c) {
        cassandraConfig.addCodec(c);
    }
}
