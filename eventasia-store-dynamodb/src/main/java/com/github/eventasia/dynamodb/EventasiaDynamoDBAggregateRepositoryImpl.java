package com.github.eventasia.dynamodb;

import com.github.eventasia.framework.Aggregate;

import java.util.UUID;

public class EventasiaDynamoDBAggregateRepositoryImpl<A extends Aggregate> implements EventasiaDynamoDBAggregateRepository<A> {

    private final EventasiaDynamoDBConfig config;
    private final Class clazz;

    public EventasiaDynamoDBAggregateRepositoryImpl(EventasiaDynamoDBConfig config, Class clazz) {
        this.config = config;
        this.clazz = clazz;
    }

    @Override
    public void save(A aggregate) {
        config.getMapper().save(aggregate);
    }

    @Override
    public A get(UUID uuid) {
        return (A) config.getMapper().load(clazz, uuid);
    }
}
