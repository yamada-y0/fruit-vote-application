package com.github.yamay0.application.port.in;

import com.github.yamay0.domain.model.Fruit;
import com.github.yamay0.domain.model.UserId;

import java.util.List;

public interface VoteFruitUseCase {
    void vote(List<Fruit> fruits, UserId userId);
}
