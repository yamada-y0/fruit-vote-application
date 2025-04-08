package com.github.yamay0.adapter.out.persistence;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.FruitRankEntry;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record FruitRankEntryEntity(
        Fruit fruit,
        Long count) {

    public FruitRankEntry toFruitRankEntry() {
        return new FruitRankEntry(
                fruit,
                count != null ? count : 0L
        );
    }
}
