package com.github.eventasia.cassandra;

import com.github.eventasia.eventstore.repository.ReadWriteAggregateRepository;
import com.github.eventasia.framework.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventasiaCassandraAggregateRepositoryImpl<A extends Aggregate> implements ReadWriteAggregateRepository<A> {

    private final EventasiaCassandraConfig eventasiaCassandraConfig;

    @Autowired
    public EventasiaCassandraAggregateRepositoryImpl(EventasiaCassandraConfig eventasiaCassandraConfig){
        this.eventasiaCassandraConfig = eventasiaCassandraConfig;
    }

    @Override
    public A get(UUID uuid) {

        return (A) eventasiaCassandraConfig.getManager().mapper(Aggregate.class).get(uuid);
    }

    @Override
    public void save(A aggregate) {
        aggregate.incrementVersion();
        eventasiaCassandraConfig.getManager().mapper(Aggregate.class).save(aggregate);
    }
}
