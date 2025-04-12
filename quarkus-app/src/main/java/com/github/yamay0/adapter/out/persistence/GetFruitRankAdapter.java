package com.github.yamay0.adapter.out.persistence;

import com.github.yamay0.application.domain.model.FruitRankEntry;
import com.github.yamay0.application.port.out.GetFruitRankPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GetFruitRankAdapter implements GetFruitRankPort {
    private final VoteRepository voteRepository;

    public GetFruitRankAdapter(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public List<FruitRankEntry> getRankedFruits() {
        return voteRepository.getRankedFruits();
    }
}
