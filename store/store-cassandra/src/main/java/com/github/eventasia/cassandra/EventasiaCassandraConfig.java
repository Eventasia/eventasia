package com.github.eventasia.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class EventasiaCassandraConfig {

    MappingManager manager;

    Mapper mapper;

    @Value("${eventasia.cassandra.contact-points}")
    private String contactPoints;

    @Value("${eventasia.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${eventasia.cassandra.port}")
    private int port;

    private Cluster cluster;

    private Session session;

    @PostConstruct
    public void connect() {
        cluster = Cluster.builder()
                .addContactPoint(getContactPoints())
                .withPort(getPort())
                .build();
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n",
                metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }

        session = cluster.connect(getKeyspace());

        session.execute("USE "+getKeyspace());

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

}

