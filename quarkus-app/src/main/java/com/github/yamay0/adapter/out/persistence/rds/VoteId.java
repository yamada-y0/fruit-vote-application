package com.github.yamay0.adapter.out.persistence.rds;

import com.github.yamay0.application.domain.model.Fruit;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class VoteId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Fruit fruit;
    private String userId;

    public VoteId() {
    }
}
