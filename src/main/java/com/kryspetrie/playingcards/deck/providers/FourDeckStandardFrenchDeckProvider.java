package com.kryspetrie.playingcards.deck.providers;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.Deck;
import com.kryspetrie.playingcards.card.standardfrench.FrenchSuitCardGroupConstants;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

/**
 * Example deck provider for a game that includes 4 decks, such as "black jack".
 * Provides a combined deck of FOUR 52-card
 *  * <a href="https://en.wikipedia.org/wiki/French-suited_playing_cards">"French"</a>
 *  * standard decks, without Jokers.
 */
public class FourDeckStandardFrenchDeckProvider implements DeckProvider {

    @Nonnull
    @Override
    public Deck provideDeck() {
        final List<Card> copy = new ArrayList<>(FrenchSuitCardGroupConstants.FULL_DECK);
        copy.addAll(FrenchSuitCardGroupConstants.FULL_DECK);
        copy.addAll(FrenchSuitCardGroupConstants.FULL_DECK);
        copy.addAll(FrenchSuitCardGroupConstants.FULL_DECK);
        return new Deck(copy);
    }
}
