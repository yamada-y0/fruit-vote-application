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
        voteRepository.persist(VoteEntity.from(fruits, userId));
    }

    @Override
    public boolean hasAlreadyVoted(Fruit fruit, UserId userId) {
        VoteEntity voteEntity = VoteEntity.from(fruit, userId);
        return voteRepository.isPersistent(voteEntity);
    }
}
