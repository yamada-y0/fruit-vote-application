package com.github.yamay0.application.domain.model;

public record FruitRankEntry(
        Fruit fruit,
        long count
) {
    public FruitRankEntry {
        if (fruit == null) {
            throw new IllegalArgumentException("Fruit cannot be null");
        }
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
    }
}
