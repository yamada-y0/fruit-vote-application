package com.github.yamay0.adapter.out.persistence;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.UserId;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "vote")
@IdClass(VoteId.class)
@Getter
public class VoteEntity {
    @Id
    private String userId;
    @Id
    @Enumerated(EnumType.STRING)
    private Fruit fruit;

    public VoteEntity() {
    }

    public static VoteEntity from(Fruit fruit, UserId userId) {
        VoteEntity entity = new VoteEntity();
        entity.userId = userId.id();
        entity.fruit = fruit;
        return entity;
    }
}
