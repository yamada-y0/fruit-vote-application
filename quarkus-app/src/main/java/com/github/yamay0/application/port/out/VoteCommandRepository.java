package com.github.yamay0.application.port.out;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.UserId;

public interface VoteCommandRepository {
    void save(Fruit fruit, UserId userId);
}
