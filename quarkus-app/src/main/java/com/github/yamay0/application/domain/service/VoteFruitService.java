package com.github.yamay0.application.domain.service;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.UserId;
import com.github.yamay0.application.port.in.VoteFruitUseCase;
import com.github.yamay0.application.port.out.VoteCommandRepository;
import com.github.yamay0.application.port.out.VoteQueryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;

@ApplicationScoped
class VoteFruitService implements VoteFruitUseCase {
    private final VoteCommandRepository voteCommandRepository;
    private final VoteQueryRepository voteQueryRepository;

    VoteFruitService(VoteCommandRepository voteCommandRepository, VoteQueryRepository voteQueryRepository) {
        this.voteCommandRepository = voteCommandRepository;
        this.voteQueryRepository = voteQueryRepository;
    }

    @Override
    @Transactional
    public void execute(List<Fruit> fruits, UserId userId) {
        if (fruits == null || fruits.isEmpty()) {
            throw new IllegalArgumentException("Fruits cannot be null or empty");
        }
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }
        Set<Fruit> uniqueFruits = Set.copyOf(fruits);
        if (uniqueFruits.size() != fruits.size()) {
            throw new IllegalArgumentException("Fruits must be unique");
        }
        fruits.stream()
                .filter(fruit -> !voteQueryRepository.exists(fruit, userId))
                .forEach(fruit -> voteCommandRepository.save(fruit, userId));
    }
}
