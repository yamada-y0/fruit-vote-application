package com.github.yamay0.scenario;

import com.github.yamay0.adapter.in.web.VoteFruitRequest;
import com.github.yamay0.application.domain.model.Fruit;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestProfile(VoteAndRankScenarioProfile.class)
public class VoteAndRankScenarioTest {
    @Test
    @TestTransaction
    @DisplayName("複数ユーザーによる投票とランキングの取得が正しく行われること")
    public void testVoteAndRank() {
        // 投票1
        given()
                .contentType(ContentType.JSON)
                .body(new VoteFruitRequest(List.of(Fruit.BANANA, Fruit.APPLE), "user1"))
                .when().post("/vote-fruit")
                .then()
                .statusCode(204);
        // 無効な投票
        given()
                .contentType(ContentType.JSON)
                .body(new VoteFruitRequest(List.of(Fruit.BANANA), "user1"))
                .when().post("/vote-fruit")
                .then()
                .statusCode(204);
        // 投票2
        given()
                .contentType(ContentType.JSON)
                .body(new VoteFruitRequest(List.of(Fruit.APPLE, Fruit.ORANGE), "user2"))
                .when().post("/vote-fruit")
                .then()
                .statusCode(204);
        // 投票3
        given()
                .contentType(ContentType.JSON)
                .body(new VoteFruitRequest(List.of(Fruit.APPLE, Fruit.BANANA), "user3"))
                .when().post("/vote-fruit")
                .then()
                .statusCode(204);

        // ランキング取得
        given()
                .contentType(ContentType.JSON)
                .when().get("/fruit-rank")
                .then()
                .statusCode(200)
                .body("[0].fruit", equalTo(Fruit.APPLE.toString()))
                .body("[0].count", equalTo(3))
                .body("[1].fruit", equalTo(Fruit.BANANA.toString()))
                .body("[1].count", equalTo(2))
                .body("[2].fruit", equalTo(Fruit.ORANGE.toString()))
                .body("[2].count", equalTo(1));
    }
}
