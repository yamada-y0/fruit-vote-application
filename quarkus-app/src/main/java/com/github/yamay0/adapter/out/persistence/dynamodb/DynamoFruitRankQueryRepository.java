package com.github.yamay0.adapter.out.persistence.dynamodb;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.FruitRankEntry;
import com.github.yamay0.application.port.out.FruitRankQueryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@ApplicationScoped
public class DynamoFruitRankQueryRepository implements FruitRankQueryRepository {
    private final DynamoDbTable<FruitCountEntity> table;

    public DynamoFruitRankQueryRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.table = dynamoDbEnhancedClient
                .table(DynamoDbTableName.FRUIT_COUNT, TableSchema.fromImmutableClass(FruitCountEntity.class));
    }

    @Override
    public List<FruitRankEntry> getRankedFruits() {
        return table.scan()
                .items()
                .stream()
                .map(entity -> new FruitRankEntry(Fruit.valueOf(entity.fruit()), entity.count()))
                .sorted((a, b) -> Long.compare(b.count(), a.count()))
                .toList();
    }
}
