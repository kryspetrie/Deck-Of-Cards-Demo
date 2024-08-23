package com.kryspetrie.playingcards.deck.actions;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.MutableCardHolder;
import jakarta.annotation.Nonnull;

import java.util.Optional;

/** {@link DeckAction} action that mutates the deck and optionally returns one card */
public interface MutateDeckAndReturnOneAction extends DeckAction {

    /**
     * Mutates the deck and optionally returns one card.
     * @param cards {@link MutableCardHolder} containing the cards.
     * @return empty, or one card.
     */
    @Nonnull
    Optional<Card> mutateAndReturnOne(@Nonnull final MutableCardHolder cards);
}
