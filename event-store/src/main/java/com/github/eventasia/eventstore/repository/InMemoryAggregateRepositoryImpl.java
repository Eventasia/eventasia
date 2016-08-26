package com.github.eventasia.eventstore.repository;

import com.github.eventasia.framework.Aggregate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@ConditionalOnMissingBean(AggregateRepository.class)
public class InMemoryAggregateRepositoryImpl<A extends Aggregate> implements ReadWriteAggregateRepository<A> {

    private Map<UUID, A> aggregateMap = new HashMap<>();

    @Override
    public void save(A aggregate) {
        aggregate.incrementVersion();
        aggregateMap.put(aggregate.getAggregateId(), aggregate);
    }

    @Override
    public A get(UUID uuid) {
        return aggregateMap.get(uuid);
    }
}
