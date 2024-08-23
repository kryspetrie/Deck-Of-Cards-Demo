package com.kryspetrie.playingcards.deck.actions;

import com.kryspetrie.playingcards.deck.MutableCardHolder;
import jakarta.annotation.Nonnull;

/** {@link DeckAction} action that mutates the deck */
public interface MutateDeckAction extends DeckAction {

    /**
     * Mutates a deck, with no return value.
     * @param cards {@link MutableCardHolder} containing the cards.
     */
    void mutate(@Nonnull final MutableCardHolder cards);
}
