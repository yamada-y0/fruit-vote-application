package com.github.yamay0.application.domain.service;

import com.github.yamay0.application.domain.model.FruitRankEntry;
import com.github.yamay0.application.port.in.GetFruitRankUseCase;
import com.github.yamay0.application.port.out.GetFruitRankPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GetFruitRankService implements GetFruitRankUseCase {
    private final GetFruitRankPort getFruitRankPort;

    public GetFruitRankService(GetFruitRankPort getFruitRankPort) {
        this.getFruitRankPort = getFruitRankPort;
    }

    @Override
    public List<FruitRankEntry> execute() {
        return getFruitRankPort.getRankedFruits();
    }
}
