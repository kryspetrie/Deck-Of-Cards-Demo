package com.kryspetrie.playingcards.deck.providers;

import com.google.common.collect.ImmutableSet;
import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.card.standardfrench.FrenchSuitCardConstants;
import com.kryspetrie.playingcards.deck.Deck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class StandardFrenchDeckProviderTest {

    private static final Set<Card> CARDS_IN_DECK = ImmutableSet.of(
            FrenchSuitCardConstants.ACE_OF_SPADES, FrenchSuitCardConstants.TWO_OF_SPADES, FrenchSuitCardConstants.THREE_OF_SPADES, FrenchSuitCardConstants.FOUR_OF_SPADES, FrenchSuitCardConstants.FIVE_OF_SPADES,
            FrenchSuitCardConstants.SIX_OF_SPADES, FrenchSuitCardConstants.SEVEN_OF_SPADES, FrenchSuitCardConstants.EIGHT_OF_SPADES, FrenchSuitCardConstants.NINE_OF_SPADES, FrenchSuitCardConstants.TEN_OF_SPADES,
            FrenchSuitCardConstants.JACK_OF_SPADES, FrenchSuitCardConstants.QUEEN_OF_SPADES, FrenchSuitCardConstants.KING_OF_SPADES,
            FrenchSuitCardConstants.ACE_OF_HEARTS, FrenchSuitCardConstants.TWO_OF_HEARTS, FrenchSuitCardConstants.THREE_OF_HEARTS, FrenchSuitCardConstants.FOUR_OF_HEARTS, FrenchSuitCardConstants.FIVE_OF_HEARTS,
            FrenchSuitCardConstants.SIX_OF_HEARTS, FrenchSuitCardConstants.SEVEN_OF_HEARTS, FrenchSuitCardConstants.EIGHT_OF_HEARTS, FrenchSuitCardConstants.NINE_OF_HEARTS, FrenchSuitCardConstants.TEN_OF_HEARTS,
            FrenchSuitCardConstants.JACK_OF_HEARTS, FrenchSuitCardConstants.QUEEN_OF_HEARTS, FrenchSuitCardConstants.KING_OF_HEARTS,
            FrenchSuitCardConstants.ACE_OF_DIAMONDS, FrenchSuitCardConstants.TWO_OF_DIAMONDS, FrenchSuitCardConstants.THREE_OF_DIAMONDS, FrenchSuitCardConstants.FOUR_OF_DIAMONDS, FrenchSuitCardConstants.FIVE_OF_DIAMONDS,
            FrenchSuitCardConstants.SIX_OF_DIAMONDS, FrenchSuitCardConstants.SEVEN_OF_DIAMONDS, FrenchSuitCardConstants.EIGHT_OF_DIAMONDS, FrenchSuitCardConstants.NINE_OF_DIAMONDS, FrenchSuitCardConstants.TEN_OF_DIAMONDS,
            FrenchSuitCardConstants.JACK_OF_DIAMONDS, FrenchSuitCardConstants.QUEEN_OF_DIAMONDS, FrenchSuitCardConstants.KING_OF_DIAMONDS,
            FrenchSuitCardConstants.ACE_OF_CLUBS, FrenchSuitCardConstants.TWO_OF_CLUBS, FrenchSuitCardConstants.THREE_OF_CLUBS, FrenchSuitCardConstants.FOUR_OF_CLUBS, FrenchSuitCardConstants.FIVE_OF_CLUBS,
            FrenchSuitCardConstants.SIX_OF_CLUBS, FrenchSuitCardConstants.SEVEN_OF_CLUBS, FrenchSuitCardConstants.EIGHT_OF_CLUBS, FrenchSuitCardConstants.NINE_OF_CLUBS, FrenchSuitCardConstants.TEN_OF_CLUBS,
            FrenchSuitCardConstants.JACK_OF_CLUBS, FrenchSuitCardConstants.QUEEN_OF_CLUBS, FrenchSuitCardConstants.KING_OF_CLUBS);

    private final DeckProvider provider = new StandardFrenchDeckProvider();

    @Test
    void testFullDeckWithJokers() {
        final Deck deck = provider.provideDeck();
        Assertions.assertThat(deck).isNotNull();
        Assertions.assertThat(deck.getCardStream()).containsAll(CARDS_IN_DECK);
    }
}