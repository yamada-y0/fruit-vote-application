package com.github.yamay0.adapter.out.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VoteRepository implements PanacheRepositoryBase<VoteEntity, VoteId> {
}
