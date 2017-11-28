package com.github.eventasia.dynamodb;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class EventasiaDynamoDBConfig {

    private DynamoDB dynamoDB;
    private DynamoDBMapper mapper;

    @Value("${eventasia.dynamodb.region}")
    private String region;
    @Value("${eventasia.dynamodb.accessKey}")
    private String accessKey;
    @Value("${eventasia.dynamodb.secretKey}")
    private String secretKey;

    @PostConstruct
    public void connect(){
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();

        dynamoDB = new DynamoDB(client);
        mapper = new DynamoDBMapper(client);
    }

    public DynamoDB getDynamoDB() {
        return dynamoDB;
    }

    public DynamoDBMapper getMapper() {
        return mapper;
    }
}
