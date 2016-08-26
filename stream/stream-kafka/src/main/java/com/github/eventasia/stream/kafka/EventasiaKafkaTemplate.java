package com.github.eventasia.stream.kafka;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;


// TODO in progress

@Component
public class EventasiaKafkaTemplate {

    private Log log = LogFactory.getLog(this.getClass());

    @Value("${eventasia.kafka.brokerList}")
    private String brokerList;

    @Value("${eventasia.kafka.topic}")
    private String topic;


    private Properties kafkaProps = new Properties();

    private Producer<String, String> producer;

    private Consumer<String, String> consumer;


    public EventasiaKafkaTemplate(){

    }

    @PostConstruct
    private void config(){
        kafkaProps.put("bootstrap.servers", getBrokerList());
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(kafkaProps);
        consumer = new KafkaConsumer<>(kafkaProps);

        consumer.subscribe(Arrays.asList(getTopic()));
    }

    private String getTopic() {
        return this.topic;
    }

    private String getBrokerList() {
        return this.brokerList;
    }

    private void send(String value) throws ExecutionException, InterruptedException {
        log.info("send.value="+value);

        ProducerRecord<String, String> record = new ProducerRecord<>(getTopic(), value);
        producer.send(record);
    }

    public void receive() {
        log.info("receiving.from="+getTopic());

        boolean running = true;
        while (running) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }

        consumer.close();
    }


}
