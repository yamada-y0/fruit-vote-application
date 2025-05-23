package com.github.yamay0.application.domain.service;

import com.github.yamay0.application.domain.model.FruitRankEntry;
import com.github.yamay0.application.port.in.GetFruitRankUseCase;
import com.github.yamay0.application.port.out.FruitRankQueryRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GetFruitRankService implements GetFruitRankUseCase {
    private final FruitRankQueryRepository fruitRankQueryRepository;

    public GetFruitRankService(FruitRankQueryRepository fruitRankQueryRepository) {
        this.fruitRankQueryRepository = fruitRankQueryRepository;
    }

    @Override
    public List<FruitRankEntry> execute() {
        return fruitRankQueryRepository.getRankedFruits();
    }
}
