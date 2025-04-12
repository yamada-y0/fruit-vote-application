package com.github.yamay0.adapter.out.persistence.dynamodb;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@RegisterForReflection
@Builder
@DynamoDbImmutable(builder = VoteEntity.VoteEntityBuilder.class)
public record VoteEntity(
        String userId,
        String fruit
) {
    @DynamoDbPartitionKey
    public String userId() {
        return userId;
    }

    @DynamoDbSortKey
    public String fruit() {
        return fruit;
    }
}
