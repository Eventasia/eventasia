package com.github.eventasia.dynamodb;

import com.github.eventasia.framework.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventasiaDynamoDBAggregateRepositoryImpl<A extends Aggregate> implements EventasiaDynamoDBAggregateRepository<A> {

    private final EventasiaDynamoDBConfig config;

    @Autowired
    public EventasiaDynamoDBAggregateRepositoryImpl(EventasiaDynamoDBConfig config) {
        this.config = config;
    }

    @Override
    public void save(A aggregate) {
        config.getMapper().save(aggregate);
    }

    @Override
    public A get(UUID uuid) {
        throw new UnsupportedOperationException("use get(Class clazz, UUID id) instead.");
    }

    @Override
    public A get(Class clazz, UUID id) {
        return (A) config.getMapper().load(clazz, id);
    }
}
