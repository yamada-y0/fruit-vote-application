package com.github.yamay0.application.domain.service;

import com.github.yamay0.application.domain.model.Fruit;
import com.github.yamay0.application.domain.model.UserId;
import com.github.yamay0.application.port.out.VoteFruitPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class VoteFruitServiceTest {
    private final VoteFruitPort voteFruitPort = mock(VoteFruitPort.class);
    private final VoteFruitService sut = new VoteFruitService(voteFruitPort);

    @Test
    @DisplayName("引数で与えられた全てのFruitに投票がされていること")
    void testExecute() {
        // given
        List<Fruit> fruits = List.of(
                Fruit.BANANA,
                Fruit.APPLE,
                Fruit.GRAPE
        );
        UserId userId = new UserId("test-user-id");

        // when
        sut.execute(fruits, userId);

        // then
        for (Fruit fruit : fruits) {
            verify(voteFruitPort).vote(fruit, userId);
        }
    }

    @Test
    @DisplayName("引数で与えられた全てのFruitに重複がある場合にIllegalArgumentExceptionが発生すること")
    void testExecuteWithDuplicateFruits() {
        // given
        List<Fruit> fruits = List.of(
                Fruit.BANANA,
                Fruit.APPLE,
                Fruit.GRAPE,
                Fruit.BANANA
        );
        UserId userId = new UserId("test-user-id");

        // when, then
        assertThrows(IllegalArgumentException.class, () -> sut.execute(fruits, userId));
        verifyNoInteractions(voteFruitPort);
    }

    @Test
    @DisplayName("引数で与えられたFruitがnullの場合にIllegalArgumentExceptionが発生すること")
    void testExecuteWithNullFruits() {
        // given
        UserId userId = new UserId("test-user-id");

        // when, then
        assertThrows(IllegalArgumentException.class, () -> sut.execute(null, userId));
        verifyNoInteractions(voteFruitPort);
    }

    @Test
    @DisplayName("引数で与えられたFruitが空の場合にIllegalArgumentExceptionが発生すること")
    void testExecuteWithEmptyFruits() {
        // given
        List<Fruit> fruits = List.of();
        UserId userId = new UserId("test-user-id");

        // when, then
        assertThrows(IllegalArgumentException.class, () -> sut.execute(fruits, userId));
        verifyNoInteractions(voteFruitPort);
    }

    @Test
    @DisplayName("引数で与えられたUserIdがnullの場合にIllegalArgumentExceptionが発生すること")
    void testExecuteWithNullUserId() {
        // given
        List<Fruit> fruits = List.of(
                Fruit.BANANA,
                Fruit.APPLE,
                Fruit.GRAPE
        );

        // when, then
        assertThrows(IllegalArgumentException.class, () -> sut.execute(fruits, null));
        verifyNoInteractions(voteFruitPort);
    }

    @Test
    @DisplayName("同じユーザーによる複数回の投票の場合に、未投票のFruitに対してのみ追加で投票されること")
    void testExecuteWithAlreadyVotedFruits() {
        // given
        List<Fruit> fruits = List.of(
                Fruit.BANANA,
                Fruit.APPLE,
                Fruit.GRAPE
        );
        UserId userId = new UserId("test-user-id");

        when(voteFruitPort.hasAlreadyVoted(Fruit.BANANA, userId)).thenReturn(true);
        when(voteFruitPort.hasAlreadyVoted(Fruit.APPLE, userId)).thenReturn(false);
        when(voteFruitPort.hasAlreadyVoted(Fruit.GRAPE, userId)).thenReturn(false);

        // when
        sut.execute(fruits, userId);

        // then
        verify(voteFruitPort).vote(Fruit.APPLE, userId);
        verify(voteFruitPort).vote(Fruit.GRAPE, userId);
        verify(voteFruitPort, never()).vote(Fruit.BANANA, userId);
    }
}
