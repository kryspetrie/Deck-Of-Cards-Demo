package com.kryspetrie.playingcards.deck.inspection;

import com.kryspetrie.playingcards.deck.Deck;
import jakarta.annotation.Nonnull;

/** Generic class for inspecting a deck and returning inspection results */
public interface DeckInspection<T> {

    /**
     * Inspect a deck and return inspection results.
     * @param deck {@link Deck} to inspect
     * @return inspection results in the desired format.
     */
    T inspect(@Nonnull final Deck deck);
}
