package com.github.yamay0.adapter.out.persistence;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class VoteRepository implements PanacheRepositoryBase<VoteEntity, VoteId> {
    public List<FruitRankEntryEntity> getRankedFruits() {
        PanacheQuery<FruitRankEntryEntity> query = find("""
                select v.fruit as fruit, count(v) as count
                from VoteEntity v
                group by v.fruit
                order by count(v) desc
                """).project(FruitRankEntryEntity.class);
        return query.stream().toList();
    }
}
