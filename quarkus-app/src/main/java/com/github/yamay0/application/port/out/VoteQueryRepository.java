package com.github.yamay0.application.port.out;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.UserId;

public interface VoteQueryRepository {
    boolean exists(Fruit fruit, UserId userId);
}
