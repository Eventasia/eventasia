package com.github.eventasia.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventasiaCassandraTemplate {

    private Log log = LogFactory.getLog(this.getClass());

    @Value("${spring.data.cassandra.contact.points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${spring.data.cassandra.port}")
    private int port;

    private Cluster cluster;

    private Session session;

    public void connect() {
        cluster = Cluster.builder()
                .addContactPoint(getContactPoints())
                .withPort(getPort())
                .build();
        Metadata metadata = cluster.getMetadata();

        log.info("Connected; cluster=" + metadata.getClusterName());

        for (Host host : metadata.getAllHosts()) {
            log.info("Datacenter=" + host.getDatacenter() + "; Host=" + host.getDatacenter() + "; Rack="+ host.getRack());
        }

        session = cluster.connect(getKeyspace());

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
}
