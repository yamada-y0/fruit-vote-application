package com.github.yamay0.adapter.in.web;

import com.github.yamay0.application.domain.model.FruitRankEntry;
import com.github.yamay0.application.port.in.GetFruitRankUseCase;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/fruit-rank")
@Produces(MediaType.APPLICATION_JSON)
public class FruitRankController {
    private final GetFruitRankUseCase getFruitRankUseCase;

    public FruitRankController(GetFruitRankUseCase getFruitRankUseCase) {
        this.getFruitRankUseCase = getFruitRankUseCase;
    }

    @GET
    public List<FruitRankEntry> getFruitRanking() {
        return getFruitRankUseCase.execute();
    }
}
