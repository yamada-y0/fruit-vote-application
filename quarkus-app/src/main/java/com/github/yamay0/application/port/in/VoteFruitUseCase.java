package com.github.yamay0.application.port.in;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.UserId;

import java.util.List;

public interface VoteFruitUseCase {
    void execute(List<Fruit> fruits, UserId userId);
}
