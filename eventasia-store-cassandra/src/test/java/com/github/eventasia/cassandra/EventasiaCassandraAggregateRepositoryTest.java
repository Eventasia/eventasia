package com.github.eventasia.cassandra;

import com.datastax.driver.core.utils.UUIDs;
import com.github.eventasia.eventstore.EnableEventasia;
import com.github.eventasia.framework.Aggregate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
public class EventasiaCassandraAggregateRepositoryTest {

    @Autowired
    private EventasiaCassandraAggregateRepository<MyAggregate> repository;

    @Before
    public void config() {
        this.repository.addMapper(MyAggregate.class);
    }

    @Ignore
    @Test
    public void testInsertAggregate() {
        MyAggregate a = new MyAggregate();
        a.setName("first");

        repository.save(a);

        Assert.assertEquals(a, repository.get(a.getAggregateId()));
    }

    @Ignore
    @Test
    public void testUpdateAggregateAndIncrementVersion() {

    }


    class MyAggregate implements Aggregate {
        UUID id = UUIDs.timeBased();
        volatile long version = 1L;
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public UUID getAggregateId() {
            return id;
        }

        @Override
        public long getVersion() {
            return version;
        }

        @Override
        public long incrementVersion() {
            return version++;
        }
    }
}
