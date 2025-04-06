package com.github.yamay0.adapter.out.persistence;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.UserId;
import com.github.yamay0.application.port.out.VoteFruitPort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class StubVoteFruitAdapter implements VoteFruitPort {
    @Override
    public void vote(Fruit fruits, UserId userId) {
    }
}
