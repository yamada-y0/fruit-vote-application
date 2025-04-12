package com.github.yamay0.adapter.out.persistence;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.FruitRankEntry;
import com.github.yamay0.application.domain.model.UserId;

import java.util.List;

public interface VoteRepository {
    void save(Fruit fruit, UserId userId);

    boolean exists(Fruit fruit, UserId userId);

    List<FruitRankEntry> getRankedFruits();
}
