package com.github.yamay0.application.port.out;

import com.github.yamay0.application.domain.model.FruitRankEntry;

import java.util.List;

public interface GetFruitRankPort {
    List<FruitRankEntry> getRankedFruits();
}
