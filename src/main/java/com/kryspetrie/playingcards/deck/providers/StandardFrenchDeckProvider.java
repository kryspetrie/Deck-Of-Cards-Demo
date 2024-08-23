package com.kryspetrie.playingcards.deck.providers;

import com.kryspetrie.playingcards.card.standardfrench.FrenchSuitCardGroupConstants;
import com.kryspetrie.playingcards.deck.Deck;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

/**
 * Deck provider for a 52-card
 * <a href="https://en.wikipedia.org/wiki/French-suited_playing_cards">"French"</a>
 * standard deck, without Jokers.
 */
@RequiredArgsConstructor
public class StandardFrenchDeckProvider implements DeckProvider {

    @Nonnull
    @Override
    public Deck provideDeck() {
        return new Deck(FrenchSuitCardGroupConstants.FULL_DECK_AS_CARD_COLLECTION);
    }
}
