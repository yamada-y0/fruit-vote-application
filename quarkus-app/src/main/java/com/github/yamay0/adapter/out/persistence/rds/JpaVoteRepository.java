package com.github.yamay0.adapter.out.persistence.rds;

import com.github.yamay0.adapter.out.persistence.VoteRepository;
import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.FruitRankEntry;
import com.github.yamay0.application.domain.model.UserId;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class JpaVoteRepository implements VoteRepository {
    private final VoteEntityPanacheRepository internalRepository;

    public JpaVoteRepository(VoteEntityPanacheRepository internalRepository) {
        this.internalRepository = internalRepository;
    }

    @Override
    public void save(Fruit fruit, UserId userId) {
        VoteEntity voteEntity = VoteEntity.from(fruit, userId);
        internalRepository.persist(voteEntity);
    }

    @Override
    public boolean exists(Fruit fruit, UserId userId) {
        return internalRepository
                .find("fruit = ?1 and userId = ?2", fruit, userId.id())
                .count() > 0;
    }

    @Override
    public List<FruitRankEntry> getRankedFruits() {
        return internalRepository.getRankedFruits()
                .stream()
                .map(FruitRankEntryEntity::toFruitRankEntry)
                .toList();
    }
}
