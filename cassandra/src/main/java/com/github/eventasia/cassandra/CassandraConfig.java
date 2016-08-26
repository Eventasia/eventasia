package com.github.eventasia.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CassandraConfig {

    private Log log = LogFactory.getLog(this.getClass());

    @Value("${eventasia.cassandra.contact-points}")
    private String contactPoints;

    @Value("${eventasia.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${eventasia.cassandra.port}")
    private String port;

    private Cluster cluster;

    private Session session;

    private MappingManager manager;

    @PostConstruct
    public void connect() {
        cluster = Cluster.builder()
                .addContactPoint(getContactPoints())
                .withPort(Integer.valueOf(getPort()))
                .build();
        Metadata metadata = cluster.getMetadata();

        log.info("Connected; cluster=" + metadata.getClusterName());

        for (Host host : metadata.getAllHosts()) {
            log.info("Datacenter=" + host.getDatacenter() + "; Host=" + host.getDatacenter() + "; Rack=" + host.getRack());
        }

        session = cluster.connect(getKeyspace());

        manager = new MappingManager(session);
    }

    private String getKeyspace() {
        return this.keyspace;
    }

    private String getPort() {
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

    public MappingManager getManager() {
        return manager;
    }
}
