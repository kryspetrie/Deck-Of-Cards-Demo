package com.kryspetrie.playingcards.deck;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.card.TestCardConstants;
import jakarta.annotation.Nonnull;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

class MutableCardHolderTest {

    /** Arguments to test method {@link MutableCardHolderTest#testSwapCardsByIndexBadArguments} */
    public static Stream<Arguments> testSwapCardsByIndexBadArgumentsParameters() {
        return Stream.of(
                Arguments.of(-1, 0),
                Arguments.of(0, -1),
                Arguments.of(0, 2),
                Arguments.of(2, 0),
                Arguments.of(-1, -1));
    }

    /** Arguments to test method {@link MutableCardHolderTest#testSwapCardsByIndex} */
    public static Stream<Arguments> testSwapCardsByIndexParameters() {
        // Starting order is: CARD_A, CARD_B, CARD_C, CARD_D, CARD_E
        return Stream.of(
                Arguments.of(0, 4, List.of(TestCardConstants.CARD_E, TestCardConstants.CARD_B, TestCardConstants.CARD_C, TestCardConstants.CARD_D, TestCardConstants.CARD_A)),
                Arguments.of(4, 0, List.of(TestCardConstants.CARD_E, TestCardConstants.CARD_B, TestCardConstants.CARD_C, TestCardConstants.CARD_D, TestCardConstants.CARD_A)),
                Arguments.of(2, 4, List.of(TestCardConstants.CARD_A, TestCardConstants.CARD_B, TestCardConstants.CARD_E, TestCardConstants.CARD_D, TestCardConstants.CARD_C)),
                Arguments.of(4, 2, List.of(TestCardConstants.CARD_A, TestCardConstants.CARD_B, TestCardConstants.CARD_E, TestCardConstants.CARD_D, TestCardConstants.CARD_C)),
                Arguments.of(0, 2, List.of(TestCardConstants.CARD_C, TestCardConstants.CARD_B, TestCardConstants.CARD_A, TestCardConstants.CARD_D, TestCardConstants.CARD_E)),
                Arguments.of(2, 0, List.of(TestCardConstants.CARD_C, TestCardConstants.CARD_B, TestCardConstants.CARD_A, TestCardConstants.CARD_D, TestCardConstants.CARD_E)),
                Arguments.of(3, 3, List.of(TestCardConstants.CARD_A, TestCardConstants.CARD_B, TestCardConstants.CARD_C, TestCardConstants.CARD_D, TestCardConstants.CARD_E)));
    }

    @Test
    void testTakeFirst() {
        // GIVEN we have three cards in the deck
        final var mutableCardHolder = new MutableCardHolder(List.of(TestCardConstants.CARD_A, TestCardConstants.CARD_B, TestCardConstants.CARD_C));

        // WHEN we try to take the first card
        final var optionalCards = mutableCardHolder.takeFirst();

        // THEN we got a result
        Assertions.assertThat(optionalCards).isPresent();
        final var cards = optionalCards.get();

        // THEN the result contains the card we expected
        Assertions.assertThat(cards).isEqualTo(TestCardConstants.CARD_A);

        // THEN the deck size has been reduced by one
        Assertions.assertThat(mutableCardHolder).hasSize(2);
        Assertions.assertThat(mutableCardHolder).containsExactly(TestCardConstants.CARD_B, TestCardConstants.CARD_C);
    }

    @Test
    void testTakeFirstUntilEmpty() {
        // GIVEN we have a deck with one card
        final var mutableCardHolder = new MutableCardHolder(List.of(TestCardConstants.CARD_A));

        // WHEN we draw the first card
        final var firstDrawOptional = mutableCardHolder.takeFirst();

        // THEN we got a result
        Assertions.assertThat(firstDrawOptional).isPresent();
        final var cards = firstDrawOptional.get();

        // THEN the result contains the card we expected
        Assertions.assertThat(cards).isEqualTo(TestCardConstants.CARD_A);

        // THEN the deck size has been reduced by one
        Assertions.assertThat(mutableCardHolder).isEmpty();

        // WHEN we draw the second card
        final var secondDrawOptional = mutableCardHolder.takeFirst();

        // THEN we did not get a result back
        Assertions.assertThat(secondDrawOptional).isEmpty();

        // THEN the deck is still empty
        Assertions.assertThat(mutableCardHolder.isEmpty()).isTrue();
    }

    @Test
    void testTakeFirstMultiple() {
        // GIVEN we have four cards in the deck
        final var mutableCardHolder = new MutableCardHolder(List.of(TestCardConstants.CARD_A, TestCardConstants.CARD_B, TestCardConstants.CARD_C, TestCardConstants.CARD_D));

        // WHEN we try to take three cards
        final var cards = mutableCardHolder.takeFirst(3);

        // THEN we got a result that contains expected cards
        Assertions.assertThat(cards).hasSize(3)
                .containsExactly(TestCardConstants.CARD_A, TestCardConstants.CARD_B, TestCardConstants.CARD_C);

        // THEN the deck size has been reduced by seven
        Assertions.assertThat(mutableCardHolder).hasSize(1);
        Assertions.assertThat(mutableCardHolder).containsExactly(TestCardConstants.CARD_D);
    }

    @Test
    void testTakeFirstMultipleWithFewerThanRequestedAvailable() {
        // GIVEN we have two cards in the deck
        final var mutableCardHolder = new MutableCardHolder(List.of(TestCardConstants.CARD_A, TestCardConstants.CARD_B));

        // WHEN we try to take three cards
        final var cards = mutableCardHolder.takeFirst(3);

        // THEN we got a result with the cards we expected
        Assertions.assertThat(cards)
                .hasSize(2)
                .containsExactly(TestCardConstants.CARD_A, TestCardConstants.CARD_B);

        // THEN the deck size has been reduced to zero
        Assertions.assertThat(mutableCardHolder).isEmpty();
    }

    @Test
    void testTakeFirstMultipleWithWithNoCardsAvailable() {
        // GIVEN an empty deck
        final var mutableCardHolder = new MutableCardHolder();

        // WHEN we try to take one card
        final var cards = mutableCardHolder.takeFirst(3);

        // THEN we got a result
        Assertions.assertThat(cards).isEmpty();
        Assertions.assertThat(mutableCardHolder).isEmpty();
    }

    @Test
    void testTakeMultipleUntilEmpty() {
        // GIVEN one card in the deck
        final var mutableCardHolder = new MutableCardHolder(List.of(TestCardConstants.CARD_A));

        // WHEN we try to draw seven cards
        final var firstDraw = mutableCardHolder.takeFirst(3);

        // THEN we got a result with the expected card
        Assertions.assertThat(firstDraw)
                .hasSize(1)
                .containsExactly(TestCardConstants.CARD_A);

        // THEN the deck size has been reduced to zero
        Assertions.assertThat(mutableCardHolder).isEmpty();

        // WHEN we try to draw seven again
        final var secondDraw = mutableCardHolder.takeFirst(3);
        Assertions.assertThat(secondDraw).isEmpty();
    }

    @ValueSource(ints = {-1, 0})
    @ParameterizedTest
    void testTakeMultipleWithBadArguments(int numberOfCards) {
        // GIVEN an empty deck
        final var mutableCardHolder = new MutableCardHolder();

        // WHEN we ask for an invalid number of cards
        // THEN we throw an exception
        Assertions.assertThatThrownBy(() -> mutableCardHolder.takeFirst(numberOfCards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Must request 1 or more cards.");
    }

    @MethodSource("testSwapCardsByIndexParameters")
    @ParameterizedTest
    void testSwapCardsByIndex(final int indexA, final int indexB, @Nonnull final List<Card> expectedOrder) {
        // GIVEN five cards in the deck
        final var mutableCardHolder = new MutableCardHolder(List.of(TestCardConstants.CARD_A, TestCardConstants.CARD_B, TestCardConstants.CARD_C, TestCardConstants.CARD_D, TestCardConstants.CARD_E));

        // WHEN we swap two cards in the deck
        mutableCardHolder.swapCardsByIndex(indexA, indexB);

        // THEN the cards are in the expected order
        Assertions.assertThat(mutableCardHolder).containsExactlyElementsOf(expectedOrder);
    }

    @MethodSource("testSwapCardsByIndexBadArgumentsParameters")
    @ParameterizedTest
    void testSwapCardsByIndexBadArguments(final int indexA, final int indexB) {
        // GIVEN two cards in the deck
        final var mutableCardHolder = new MutableCardHolder(List.of(TestCardConstants.CARD_A, TestCardConstants.CARD_B));

        // WHEN we ask to swap cards by invalid indexes
        // THEN we throw an exception
        Assertions.assertThatThrownBy(() -> mutableCardHolder.swapCardsByIndex(indexA, indexB))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Indexes must be within range.");
    }
}