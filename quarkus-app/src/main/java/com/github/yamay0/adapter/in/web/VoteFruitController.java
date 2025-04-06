package com.github.yamay0.adapter.in.web;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.UserId;
import com.github.yamay0.application.port.in.VoteFruitUseCase;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/vote-fruit")
public class VoteFruitController {
    private final VoteFruitUseCase voteFruitUseCase;

    public VoteFruitController(VoteFruitUseCase voteFruitUseCase) {
        this.voteFruitUseCase = voteFruitUseCase;
    }

    @POST
    public void voteFruit(@Valid VoteFruitRequest request) {
        List<Fruit> fruits = request.fruits();
        UserId userId = new UserId(request.userId());
        voteFruitUseCase.execute(fruits, userId);
    }
}
