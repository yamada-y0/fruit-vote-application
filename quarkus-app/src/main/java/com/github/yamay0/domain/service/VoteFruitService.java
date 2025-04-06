package com.github.yamay0.domain.service;

import com.github.yamay0.application.port.in.VoteFruitUseCase;
import com.github.yamay0.application.port.out.VoteFruitPort;
import com.github.yamay0.domain.model.Fruit;
import com.github.yamay0.domain.model.UserId;

import java.util.List;
import java.util.Set;

class VoteFruitService implements VoteFruitUseCase {
    private final VoteFruitPort voteFruitPort;

    VoteFruitService(VoteFruitPort voteFruitPort) {
        this.voteFruitPort = voteFruitPort;
    }

    @Override
    public void vote(List<Fruit> fruits, UserId userId) {
        if (fruits == null || fruits.isEmpty()) {
            throw new IllegalArgumentException("Fruits cannot be null or empty");
        }
        Set<Fruit> uniqueFruits = Set.copyOf(fruits);
        if (uniqueFruits.size() != fruits.size()) {
            throw new IllegalArgumentException("Fruits must be unique");
        }
        fruits.forEach(fruit -> voteFruitPort.vote(fruit, userId));
    }
}
