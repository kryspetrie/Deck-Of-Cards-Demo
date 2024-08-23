package com.kryspetrie.playingcards.card.standardfrench;

import com.google.common.collect.ImmutableList;
import com.kryspetrie.playingcards.card.Card;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Defines common groupings of {@link FrenchSuitCard} */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FrenchSuitCardGroupConstants {

    public static final List<FrenchSuitCard> SPADES = ImmutableList.of(
            FrenchSuitCardConstants.ACE_OF_SPADES, FrenchSuitCardConstants.TWO_OF_SPADES, FrenchSuitCardConstants.THREE_OF_SPADES, FrenchSuitCardConstants.FOUR_OF_SPADES, FrenchSuitCardConstants.FIVE_OF_SPADES,
            FrenchSuitCardConstants.SIX_OF_SPADES, FrenchSuitCardConstants.SEVEN_OF_SPADES, FrenchSuitCardConstants.EIGHT_OF_SPADES, FrenchSuitCardConstants.NINE_OF_SPADES, FrenchSuitCardConstants.TEN_OF_SPADES,
            FrenchSuitCardConstants.JACK_OF_SPADES, FrenchSuitCardConstants.QUEEN_OF_SPADES, FrenchSuitCardConstants.KING_OF_SPADES);

    public static final List<FrenchSuitCard> HEARTS = ImmutableList.of(
            FrenchSuitCardConstants.ACE_OF_HEARTS, FrenchSuitCardConstants.TWO_OF_HEARTS, FrenchSuitCardConstants.THREE_OF_HEARTS, FrenchSuitCardConstants.FOUR_OF_HEARTS, FrenchSuitCardConstants.FIVE_OF_HEARTS,
            FrenchSuitCardConstants.SIX_OF_HEARTS, FrenchSuitCardConstants.SEVEN_OF_HEARTS, FrenchSuitCardConstants.EIGHT_OF_HEARTS, FrenchSuitCardConstants.NINE_OF_HEARTS, FrenchSuitCardConstants.TEN_OF_HEARTS,
            FrenchSuitCardConstants.JACK_OF_HEARTS, FrenchSuitCardConstants.QUEEN_OF_HEARTS, FrenchSuitCardConstants.KING_OF_HEARTS);

    public static final List<FrenchSuitCard> DIAMONDS = ImmutableList.of(
            FrenchSuitCardConstants.ACE_OF_DIAMONDS, FrenchSuitCardConstants.TWO_OF_DIAMONDS, FrenchSuitCardConstants.THREE_OF_DIAMONDS, FrenchSuitCardConstants.FOUR_OF_DIAMONDS, FrenchSuitCardConstants.FIVE_OF_DIAMONDS,
            FrenchSuitCardConstants.SIX_OF_DIAMONDS, FrenchSuitCardConstants.SEVEN_OF_DIAMONDS, FrenchSuitCardConstants.EIGHT_OF_DIAMONDS, FrenchSuitCardConstants.NINE_OF_DIAMONDS, FrenchSuitCardConstants.TEN_OF_DIAMONDS,
            FrenchSuitCardConstants.JACK_OF_DIAMONDS, FrenchSuitCardConstants.QUEEN_OF_DIAMONDS, FrenchSuitCardConstants.KING_OF_DIAMONDS);

    public static final List<FrenchSuitCard> CLUBS = ImmutableList.of(
            FrenchSuitCardConstants.ACE_OF_CLUBS, FrenchSuitCardConstants.TWO_OF_CLUBS, FrenchSuitCardConstants.THREE_OF_CLUBS, FrenchSuitCardConstants.FOUR_OF_CLUBS, FrenchSuitCardConstants.FIVE_OF_CLUBS,
            FrenchSuitCardConstants.SIX_OF_CLUBS, FrenchSuitCardConstants.SEVEN_OF_CLUBS, FrenchSuitCardConstants.EIGHT_OF_CLUBS, FrenchSuitCardConstants.NINE_OF_CLUBS, FrenchSuitCardConstants.TEN_OF_CLUBS,
            FrenchSuitCardConstants.JACK_OF_CLUBS, FrenchSuitCardConstants.QUEEN_OF_CLUBS, FrenchSuitCardConstants.KING_OF_CLUBS);

    public static final List<FrenchSuitCard> JOKERS = ImmutableList.of(FrenchSuitCardConstants.BLACK_JOKER, FrenchSuitCardConstants.RED_JOKER);

    public static final List<FrenchSuitCard> FULL_DECK =
            Stream.of(SPADES, HEARTS, DIAMONDS, CLUBS)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

    public static final List<FrenchSuitCard> FULL_DECK_WITH_JOKERS =
            Stream.of(SPADES, HEARTS, DIAMONDS, CLUBS, JOKERS)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

    public static final Collection<Card> FULL_DECK_AS_CARD_COLLECTION =
            FULL_DECK.stream().collect(Collectors.toUnmodifiableList());

    public static final Collection<Card> FULL_DECK_WITH_JOKERS_AS_CARD_COLLECTION =
            FULL_DECK_WITH_JOKERS.stream().collect(Collectors.toUnmodifiableList());
}
