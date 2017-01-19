package com.github.eventasia.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.extras.codecs.enums.EnumNameCodec;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.github.eventasia.framework.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class EventasiaCassandraConfig {

    private MappingManager manager;

    private Mapper mapper;

    @Value("${eventasia.cassandra.contact-points}")
    private String contactPoints;

    @Value("${eventasia.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${eventasia.cassandra.port}")
    private int port;

    private Cluster cluster;

    private Session session;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @PostConstruct
    public void connect() {
        cluster = Cluster.builder()
                .addContactPoint(getContactPoints())
                .withPort(getPort())
                .withPoolingOptions(new PoolingOptions())
                .build();
        Metadata metadata = cluster.getMetadata();
        log.info("Connected to cluster: "+ metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            log.info("Datacenter: "+host.getDatacenter()+" Host: "+host.getAddress()+ " Rack: " + host.getRack());
        }

        session = cluster.connect(getKeyspace());

        manager = new MappingManager(session);
    }

    private String getKeyspace() {
        return this.keyspace;
    }

    private int getPort() {
        return this.port;
    }

    private String getContactPoints() {
        return this.contactPoints;
    }

    private void close() {
        session.close();
        cluster.close();
    }

    public Session getSession() {
        if (this.session == null) {
            this.connect();
        }

        return this.session;
    }

    public Mapper getMapper() {
        return mapper;
    }

    public MappingManager getManager() {
        return manager;
    }

    public <A extends Aggregate> void addMapper(Class<A> c) {
        mapper = this.getManager().mapper(c);
    }

    public void addUDT(Class c) {
        manager.udtCodec(c);
    }

    public void addCodec(Class c) {
        cluster.getConfiguration().getCodecRegistry()
                .register(new EnumNameCodec<>(c));
    }
}

