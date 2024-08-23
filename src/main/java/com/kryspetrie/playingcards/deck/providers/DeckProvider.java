package com.kryspetrie.playingcards.deck.providers;

import com.kryspetrie.playingcards.deck.Deck;
import jakarta.annotation.Nonnull;

public interface DeckProvider {

    @Nonnull
    Deck provideDeck();
}
