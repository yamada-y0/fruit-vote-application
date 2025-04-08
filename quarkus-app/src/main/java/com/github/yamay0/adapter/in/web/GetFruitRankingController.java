package com.github.yamay0.adapter.in.web;

import com.github.yamay0.application.domain.model.FruitRankEntry;
import com.github.yamay0.application.port.in.GetFruitRankUseCase;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/fruit-ranking")
public class GetFruitRankingController {
    private final GetFruitRankUseCase getFruitRankUseCase;

    public GetFruitRankingController(GetFruitRankUseCase getFruitRankUseCase) {
        this.getFruitRankUseCase = getFruitRankUseCase;
    }

    @GET
    public List<FruitRankEntry> getFruitRanking() {
        return getFruitRankUseCase.execute();
    }
}
