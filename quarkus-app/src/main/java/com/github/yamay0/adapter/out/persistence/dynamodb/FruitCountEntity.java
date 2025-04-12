package com.github.yamay0.adapter.out.persistence.dynamodb;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import software.amazon.awssdk.enhanced.dynamodb.extensions.annotations.DynamoDbAtomicCounter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@RegisterForReflection
@Builder
@DynamoDbImmutable(builder = FruitCountEntity.FruitCountEntityBuilder.class)
public record FruitCountEntity(
        String fruit,
        Long count
) {
    @DynamoDbPartitionKey
    public String fruit() {
        return fruit;
    }

    @DynamoDbAtomicCounter(startValue = 1L)
    public Long count() {
        return count;
    }
}
