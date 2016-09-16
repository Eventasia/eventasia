package com.github.eventasia.cassandra;

import com.github.eventasia.framework.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventasiaCassandraAggregateRepositoryImpl<A extends Aggregate> implements EventasiaCassandraAggregateRepository<A>{

    @Autowired
    private EventasiaCassandraConfig eventasiaCassandraConfig;

    // TODO add id to keep all versions of aggregate
    @Override
    public A get(UUID uuid) {

        return (A) eventasiaCassandraConfig.getMapper().get(uuid);
    }

    @Override
    public void save(A aggregate) {
        aggregate.incrementVersion();
        eventasiaCassandraConfig.getMapper().save(aggregate);
    }

    @Override
    public void addMapper(Class<A> c) {
        eventasiaCassandraConfig.addMapper(c);
    }

    @Override
    public void addUDT(Class c) {
        eventasiaCassandraConfig.addUDT(c);
    }

    @Override
    public void addCodec(Class c) {
        eventasiaCassandraConfig.addCodec(c);
    }
}
