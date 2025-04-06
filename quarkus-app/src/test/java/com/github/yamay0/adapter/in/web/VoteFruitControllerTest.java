package com.github.yamay0.adapter.in.web;

import com.github.yamay0.application.domain.model.Fruit;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class VoteFruitControllerTest {
    @Test
    @DisplayName("投票が成功すること")
    public void testVoteFruit() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteFruitRequest(List.of(Fruit.BANANA, Fruit.APPLE), "userId"))
                .when().post("/vote-fruit")
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("リクエストに含まれるFruitがnullの場合、400エラーが返ること")
    public void testVoteFruitWithNullFruits() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteFruitRequest(null, "userId"))
                .when().post("/vote-fruit")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("リクエストに含まれるFruitが空リストの場合、400エラーが返ること")
    public void testVoteFruitWithEmptyFruits() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteFruitRequest(List.of(), "userId"))
                .when().post("/vote-fruit")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("リクエストに含まれるuserIdがnullの場合、400エラーが返ること")
    public void testVoteFruitWithNullUserId() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteFruitRequest(List.of(Fruit.BANANA, Fruit.APPLE), null))
                .when().post("/vote-fruit")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("リクエストに含まれるuserIdが空文字の場合、400エラーが返ること")
    public void testVoteFruitWithEmptyUserId() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteFruitRequest(List.of(Fruit.BANANA, Fruit.APPLE), ""))
                .when().post("/vote-fruit")
                .then()
                .statusCode(400);
    }
}
