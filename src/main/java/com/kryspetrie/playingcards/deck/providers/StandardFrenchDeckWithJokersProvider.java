package com.kryspetrie.playingcards.deck.providers;

import com.kryspetrie.playingcards.deck.Deck;
import com.kryspetrie.playingcards.card.standardfrench.FrenchSuitCardGroupConstants;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

/**
 * Example deck provider for a 52-card
 * <a href="https://en.wikipedia.org/wiki/French-suited_playing_cards">"French"</a>
 * standard deck, including Jokers.
 */
@RequiredArgsConstructor
public class StandardFrenchDeckWithJokersProvider implements DeckProvider {

    @Nonnull
    @Override
    public Deck provideDeck() {
        return new Deck(FrenchSuitCardGroupConstants.FULL_DECK_WITH_JOKERS_AS_CARD_COLLECTION);
    }
}
