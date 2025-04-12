package com.github.yamay0.adapter.out.persistence.dynamodb;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.ResourceInUseException;

@Singleton
@Startup
public class DynamoDbTableInitializer {
    private final boolean isDevServicesEnabled;
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public DynamoDbTableInitializer(
            DynamoDbEnhancedClient dynamoDbEnhancedClient,
            @ConfigProperty(name = "quarkus.dynamodb.devservices.enabled", defaultValue = "true")
            boolean isDevServicesEnabled) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        this.isDevServicesEnabled = isDevServicesEnabled;
    }

    @PostConstruct
    public void init() {
        if (!isDevServicesEnabled) {
            return;
        }

        createVoteTable();
        createFruitCountTable();
    }

    private void createFruitCountTable() {
        try {
            dynamoDbEnhancedClient
                    .table(DynamoDbTableName.FRUIT_COUNT, TableSchema.fromImmutableClass(FruitCountEntity.class))
                    .createTable();
        } catch (ResourceInUseException e) {
            // Table already exists, do nothing
        }
    }

    private void createVoteTable() {
        try {
            dynamoDbEnhancedClient
                    .table(DynamoDbTableName.VOTE, TableSchema.fromImmutableClass(VoteEntity.class))
                    .createTable();
        } catch (ResourceInUseException e) {
            // Table already exists, do nothing
        }
    }
}
