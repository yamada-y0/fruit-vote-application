package com.github.yamay0.adapter.out.persistence;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.UserId;
import com.github.yamay0.application.port.out.VoteFruitPort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VoteFruitAdapter implements VoteFruitPort {
    private final VoteRepository voteRepository;

    public VoteFruitAdapter(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public void vote(Fruit fruits, UserId userId) {
        voteRepository.save(fruits, userId);
    }

    @Override
    public boolean hasAlreadyVoted(Fruit fruit, UserId userId) {
        return voteRepository.exists(fruit, userId);
    }
}
