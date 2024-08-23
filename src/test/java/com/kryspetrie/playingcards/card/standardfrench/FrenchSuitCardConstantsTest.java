package com.kryspetrie.playingcards.card.standardfrench;

import com.google.common.collect.ImmutableSet;
import jakarta.annotation.Nonnull;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test that we have defined our constants in a consistent and expected way.
 * Redefine the groups of cards, as to not also be testing the groupings.
 */
class FrenchSuitCardConstantsTest {

    private static final Set<FrenchSuitCard> TEST_SPADES = ImmutableSet.of(
            FrenchSuitCardConstants.ACE_OF_SPADES, FrenchSuitCardConstants.TWO_OF_SPADES, FrenchSuitCardConstants.THREE_OF_SPADES, FrenchSuitCardConstants.FOUR_OF_SPADES, FrenchSuitCardConstants.FIVE_OF_SPADES,
            FrenchSuitCardConstants.SIX_OF_SPADES, FrenchSuitCardConstants.SEVEN_OF_SPADES, FrenchSuitCardConstants.EIGHT_OF_SPADES, FrenchSuitCardConstants.NINE_OF_SPADES, FrenchSuitCardConstants.TEN_OF_SPADES,
            FrenchSuitCardConstants.JACK_OF_SPADES, FrenchSuitCardConstants.QUEEN_OF_SPADES, FrenchSuitCardConstants.KING_OF_SPADES);

    private static final Set<FrenchSuitCard> TEST_HEARTS = ImmutableSet.of(
            FrenchSuitCardConstants.ACE_OF_HEARTS, FrenchSuitCardConstants.TWO_OF_HEARTS, FrenchSuitCardConstants.THREE_OF_HEARTS, FrenchSuitCardConstants.FOUR_OF_HEARTS, FrenchSuitCardConstants.FIVE_OF_HEARTS,
            FrenchSuitCardConstants.SIX_OF_HEARTS, FrenchSuitCardConstants.SEVEN_OF_HEARTS, FrenchSuitCardConstants.EIGHT_OF_HEARTS, FrenchSuitCardConstants.NINE_OF_HEARTS, FrenchSuitCardConstants.TEN_OF_HEARTS,
            FrenchSuitCardConstants.JACK_OF_HEARTS, FrenchSuitCardConstants.QUEEN_OF_HEARTS, FrenchSuitCardConstants.KING_OF_HEARTS);

    private static final Set<FrenchSuitCard> TEST_DIAMONDS = ImmutableSet.of(
            FrenchSuitCardConstants.ACE_OF_DIAMONDS, FrenchSuitCardConstants.TWO_OF_DIAMONDS, FrenchSuitCardConstants.THREE_OF_DIAMONDS, FrenchSuitCardConstants.FOUR_OF_DIAMONDS, FrenchSuitCardConstants.FIVE_OF_DIAMONDS,
            FrenchSuitCardConstants.SIX_OF_DIAMONDS, FrenchSuitCardConstants.SEVEN_OF_DIAMONDS, FrenchSuitCardConstants.EIGHT_OF_DIAMONDS, FrenchSuitCardConstants.NINE_OF_DIAMONDS, FrenchSuitCardConstants.TEN_OF_DIAMONDS,
            FrenchSuitCardConstants.JACK_OF_DIAMONDS, FrenchSuitCardConstants.QUEEN_OF_DIAMONDS, FrenchSuitCardConstants.KING_OF_DIAMONDS);

    private static final Set<FrenchSuitCard> TEST_CLUBS = ImmutableSet.of(
            FrenchSuitCardConstants.ACE_OF_CLUBS, FrenchSuitCardConstants.TWO_OF_CLUBS, FrenchSuitCardConstants.THREE_OF_CLUBS, FrenchSuitCardConstants.FOUR_OF_CLUBS, FrenchSuitCardConstants.FIVE_OF_CLUBS,
            FrenchSuitCardConstants.SIX_OF_CLUBS, FrenchSuitCardConstants.SEVEN_OF_CLUBS, FrenchSuitCardConstants.EIGHT_OF_CLUBS, FrenchSuitCardConstants.NINE_OF_CLUBS, FrenchSuitCardConstants.TEN_OF_CLUBS,
            FrenchSuitCardConstants.JACK_OF_CLUBS, FrenchSuitCardConstants.QUEEN_OF_CLUBS, FrenchSuitCardConstants.KING_OF_CLUBS);

    private static final Set<FrenchType> TEST_TYPES = ImmutableSet.of(
            FrenchType.ACE, FrenchType.TWO, FrenchType.THREE, FrenchType.FOUR, FrenchType.FIVE, FrenchType.SIX, FrenchType.SEVEN, FrenchType.EIGHT, FrenchType.NINE, FrenchType.TEN, FrenchType.JACK, FrenchType.QUEEN, FrenchType.KING);

    private static final Set<String> TEST_SUITS_NAME_PREFIXES = ImmutableSet.of(
            "Ace of ", "Two of ", "Three of ", "Four of ", "Five of ", "Six of ", "Seven of ",
            "Eight of ", "Nine of ", "Ten of ", "Jack of ", "Queen of ", "King of ");

    /** Parameters for {@link FrenchSuitCardConstantsTest#testCardsSuit} */
    static Stream<Arguments> testCardsSuitParameters() {
        return Stream.of(
                Arguments.of(TEST_SPADES, FrenchSuit.SPADES),
                Arguments.of(TEST_HEARTS, FrenchSuit.HEARTS),
                Arguments.of(TEST_DIAMONDS, FrenchSuit.DIAMONDS),
                Arguments.of(TEST_CLUBS, FrenchSuit.CLUBS)
        );
    }

    /** Parameters for {@link FrenchSuitCardConstantsTest#testCardsColor} */
    static Stream<Arguments> testCardsColorParameters() {
        return Stream.of(
                Arguments.of(TEST_SPADES, FrenchColor.BLACK),
                Arguments.of(TEST_HEARTS, FrenchColor.RED),
                Arguments.of(TEST_DIAMONDS, FrenchColor.RED),
                Arguments.of(TEST_CLUBS, FrenchColor.BLACK)
        );
    }

    /** Parameters for {@link FrenchSuitCardConstantsTest#testCardsType} */
    static Stream<Set<FrenchSuitCard>> testCardsTypeParameters() {
        return Stream.of(TEST_SPADES, TEST_HEARTS, TEST_DIAMONDS, TEST_CLUBS);
    }

    /** Parameters for {@link FrenchSuitCardConstantsTest#testCardsName} */
    public static Stream<Arguments> testCardsNameParameters() {
        return Stream.of(
                Arguments.of(TEST_SPADES, "Spades"),
                Arguments.of(TEST_HEARTS, "Hearts"),
                Arguments.of(TEST_DIAMONDS, "Diamonds"),
                Arguments.of(TEST_CLUBS, "Clubs")
        );
    }

    /** Parameters for {@link FrenchSuitCardConstantsTest#testJokers} */
    public static Stream<Arguments> testJokersParameters() {
        return Stream.of(
                Arguments.of(FrenchSuitCardConstants.BLACK_JOKER, FrenchColor.BLACK, "Black Joker"),
                Arguments.of(FrenchSuitCardConstants.RED_JOKER, FrenchColor.RED, "Red Joker"));
    }

    @ParameterizedTest
    @MethodSource("testCardsSuitParameters")
    void testCardsSuit(@Nonnull Set<FrenchSuitCard> cards, @Nonnull FrenchSuit suit) {
        Assertions.assertThat(cards)
                .map(FrenchSuitCard::getSuit)
                .as("Suit should be " + suit)
                .allMatch(suit::equals);
    }

    @ParameterizedTest
    @MethodSource("testCardsColorParameters")
    void testCardsColor(@Nonnull Set<FrenchSuitCard> cards, @Nonnull FrenchColor color) {
        Assertions.assertThat(cards)
                .map(FrenchSuitCard::getColor)
                .as("Color should be " + color)
                .allMatch(color::equals);
    }

    @ParameterizedTest
    @MethodSource("testCardsTypeParameters")
    void testCardsType(@Nonnull Set<FrenchSuitCard> cards) {
        Assertions.assertThat(cards)
                .map(FrenchSuitCard::getType)
                .as("Should contain all possible types: " + TEST_TYPES)
                .containsAll(TEST_TYPES);
    }

    @ParameterizedTest
    @MethodSource("testCardsNameParameters")
    void testCardsName(@Nonnull Set<FrenchSuitCard> cards, @Nonnull String suitName) {
        Assertions.assertThat(cards)
                .as("Names should contain the value")
                .allSatisfy((FrenchSuitCard card) -> {
                    var name = card.getName();
                    var type = card.getType();
                    Assertions.assertThat(name).startsWithIgnoringCase(type.name());
                });

        final Set<String> expectedNames = TEST_SUITS_NAME_PREFIXES.stream()
                .map(it -> it + suitName)
                .collect(Collectors.toSet());

        Assertions.assertThat(cards)
                .map(FrenchSuitCard::getName)
                .as("Names should be in a human readable form")
                .allMatch(expectedNames::contains);
    }

    @ParameterizedTest
    @MethodSource("testJokersParameters")
    void testJokers(@Nonnull FrenchSuitCard card, @Nonnull FrenchColor color, @Nonnull String name) {
        SoftAssertions.assertSoftly(softly -> {

            // Validate the type
            softly.assertThat(card.getType())
                    .as("Card should be a joker")
                    .isEqualTo(FrenchType.JOKER);

            // Validate the suit
            softly.assertThat(card.getSuit())
                        .as("Should be a joker")
                        .isEqualTo(FrenchSuit.NONE);

            // Validate the color
            softly.assertThat(card.getColor())
                    .as("Color should be " + color)
                    .isEqualTo(color);

            // Validate the name
            softly.assertThat(card.getName())
                    .as("Name should be \"" + name + "\"")
                    .endsWith(name);
        });
    }
}