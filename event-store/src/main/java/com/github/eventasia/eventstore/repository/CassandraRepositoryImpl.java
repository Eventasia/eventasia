package com.github.eventasia.eventstore.repository;


import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.github.eventasia.cassandra.EventasiaCassandraTemplate;
import com.github.eventasia.framework.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CassandraRepositoryImpl<A extends Aggregate> implements ReadWriteAggregateRepository<A> {

    @Autowired
    EventasiaCassandraTemplate eventasiaCassandraTemplate;

    MappingManager manager;

    Mapper<Aggregate> mapper;

    public CassandraRepositoryImpl(){
        manager = new MappingManager(eventasiaCassandraTemplate.getSession());
        mapper = manager.mapper(Aggregate.class);
    }

    @Override
    public A get(UUID uuid) {

        return (A)mapper.get(uuid);
    }

    @Override
    public void save(A aggregate) {
        mapper.save(aggregate);
    }
}