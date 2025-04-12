package com.github.yamay0.adapter.out.persistence.dynamodb;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.UserId;
import com.github.yamay0.application.port.out.VoteCommandRepository;
import com.github.yamay0.application.port.out.VoteQueryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@ApplicationScoped
public class DynamoVoteCommandRepository implements VoteCommandRepository, VoteQueryRepository {
    private final DynamoDbEnhancedClient client;
    private final DynamoDbTable<VoteEntity> voteTable;
    private final DynamoDbTable<FruitCountEntity> fruitCountTable;

    @Inject
    public DynamoVoteCommandRepository(DynamoDbEnhancedClient client) {
        this.client = client;
        this.voteTable = client
                .table(DynamoDbTableName.VOTE, TableSchema.fromImmutableClass(VoteEntity.class));
        this.fruitCountTable = client
                .table(DynamoDbTableName.FRUIT_COUNT, TableSchema.fromImmutableClass(FruitCountEntity.class));
    }

    @Override
    public void save(Fruit fruit, UserId userId) {
        client.transactWriteItems(builder -> builder
                .addPutItem(voteTable, new VoteEntity(userId.id(), fruit.name()))
                .addUpdateItem(fruitCountTable, new FruitCountEntity(fruit.name(), null)));
    }

    @Override
    public boolean exists(Fruit fruit, UserId userId) {
        return voteTable.getItem(new VoteEntity(userId.id(), fruit.name())) != null;
    }
}
