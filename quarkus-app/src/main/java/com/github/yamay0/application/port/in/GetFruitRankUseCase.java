package com.github.yamay0.application.port.in;

import com.github.yamay0.application.domain.model.FruitRankEntry;

import java.util.List;

public interface GetFruitRankUseCase {
    List<FruitRankEntry> execute();
}
