package com.kryspetrie.playingcards.card.standardfrench;


import com.google.common.collect.ImmutableList;
import jakarta.annotation.Nonnull;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kryspetrie.playingcards.card.standardfrench.FrenchSuitCardConstants.*;

/**
 * Test the constants groupings, redefining them here. This basically validates that nobody
 * accidentally changes the list without also updating the corresponding test.
 */
class FrenchSuitCardGroupConstantsTest {

    private static final List<FrenchSuitCard> TEST_SPADES = ImmutableList.of(
            ACE_OF_SPADES, TWO_OF_SPADES, THREE_OF_SPADES, FOUR_OF_SPADES, FIVE_OF_SPADES,
            SIX_OF_SPADES, SEVEN_OF_SPADES, EIGHT_OF_SPADES, NINE_OF_SPADES, TEN_OF_SPADES,
            JACK_OF_SPADES, QUEEN_OF_SPADES, KING_OF_SPADES);

    private static final List<FrenchSuitCard> TEST_HEARTS = ImmutableList.of(
            ACE_OF_HEARTS, TWO_OF_HEARTS, THREE_OF_HEARTS, FOUR_OF_HEARTS, FIVE_OF_HEARTS,
            SIX_OF_HEARTS, SEVEN_OF_HEARTS, EIGHT_OF_HEARTS, NINE_OF_HEARTS, TEN_OF_HEARTS,
            JACK_OF_HEARTS, QUEEN_OF_HEARTS, KING_OF_HEARTS);

    private static final List<FrenchSuitCard> TEST_DIAMONDS = ImmutableList.of(
            ACE_OF_DIAMONDS, TWO_OF_DIAMONDS, THREE_OF_DIAMONDS, FOUR_OF_DIAMONDS, FIVE_OF_DIAMONDS,
            SIX_OF_DIAMONDS, SEVEN_OF_DIAMONDS, EIGHT_OF_DIAMONDS, NINE_OF_DIAMONDS, TEN_OF_DIAMONDS,
            JACK_OF_DIAMONDS, QUEEN_OF_DIAMONDS, KING_OF_DIAMONDS);

    private static final List<FrenchSuitCard> TEST_CLUBS = ImmutableList.of(
            ACE_OF_CLUBS, TWO_OF_CLUBS, THREE_OF_CLUBS, FOUR_OF_CLUBS, FIVE_OF_CLUBS,
            SIX_OF_CLUBS, SEVEN_OF_CLUBS, EIGHT_OF_CLUBS, NINE_OF_CLUBS, TEN_OF_CLUBS,
            JACK_OF_CLUBS, QUEEN_OF_CLUBS, KING_OF_CLUBS);

    private static final List<FrenchSuitCard> TEST_FULL_DECK = ImmutableList.copyOf(
            Stream.of(TEST_SPADES, TEST_HEARTS, TEST_DIAMONDS, TEST_CLUBS)
                    .flatMap(List::stream)
                    .collect(Collectors.toList()));

    private static final List<FrenchSuitCard> TEST_FULL_DECK_WITH_JOKERS = ImmutableList.copyOf(
          Stream.of(TEST_FULL_DECK.stream(), Stream.of(BLACK_JOKER, RED_JOKER))
                  .flatMap(Function.identity())
                  .collect(Collectors.toList()));

    /** Parameters for {@link FrenchSuitCardGroupConstantsTest#testSuits} */
    static Stream<Arguments> testSuitsParameters() {
        return Stream.of(
                Arguments.of(FrenchSuitCardGroupConstants.SPADES, TEST_SPADES),
                Arguments.of(FrenchSuitCardGroupConstants.HEARTS, TEST_HEARTS),
                Arguments.of(FrenchSuitCardGroupConstants.DIAMONDS, TEST_DIAMONDS),
                Arguments.of(FrenchSuitCardGroupConstants.CLUBS, TEST_CLUBS));
    }

    @ParameterizedTest
    @MethodSource("testSuitsParameters")
    void testSuits(@Nonnull List<FrenchSuitCard> constants, @Nonnull List<FrenchSuitCard> testConstants) {
        Assertions.assertThat(FrenchSuitCardGroupConstants.SPADES)
                .hasSameSizeAs(TEST_SPADES)
                .containsExactlyElementsOf(TEST_SPADES);
    }

    @Test
    void testDeckWithoutJokers() {
        Assertions.assertThat(FrenchSuitCardGroupConstants.FULL_DECK)
                .hasSameSizeAs(TEST_FULL_DECK)
                .containsExactlyElementsOf(TEST_FULL_DECK);
    }

    @Test
    void testDeckWithJokers() {
        Assertions.assertThat(FrenchSuitCardGroupConstants.FULL_DECK_WITH_JOKERS)
                .hasSameSizeAs(TEST_FULL_DECK_WITH_JOKERS)
                .containsExactlyElementsOf(TEST_FULL_DECK_WITH_JOKERS);
    }

    @Test
    void testDeckWithoutJokersAsCardCollection() {
        Assertions.assertThat(FrenchSuitCardGroupConstants.FULL_DECK_AS_CARD_COLLECTION)
                .containsExactlyElementsOf(FrenchSuitCardGroupConstants.FULL_DECK);
    }

    @Test
    void testDeckWithJokersAsCardCollection() {
        Assertions.assertThat(FrenchSuitCardGroupConstants.FULL_DECK_WITH_JOKERS_AS_CARD_COLLECTION)
                .containsExactlyElementsOf(FrenchSuitCardGroupConstants.FULL_DECK_WITH_JOKERS);
    }
}