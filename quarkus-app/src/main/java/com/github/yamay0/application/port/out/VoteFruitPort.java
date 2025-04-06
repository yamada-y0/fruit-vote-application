package com.github.yamay0.application.port.out;

import com.github.yamay0.domain.model.Fruit;
import com.github.yamay0.domain.model.UserId;

public interface VoteFruitPort {
    void vote(Fruit fruits, UserId userId);
}
