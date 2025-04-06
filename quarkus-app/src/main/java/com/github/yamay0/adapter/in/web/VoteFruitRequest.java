package com.github.yamay0.adapter.in.web;

import com.github.yamay0.application.domain.model.Fruit;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record VoteFruitRequest(
        @Nonnull @NotEmpty List<Fruit> fruits,
        @Nonnull @NotEmpty String userId) {
}
