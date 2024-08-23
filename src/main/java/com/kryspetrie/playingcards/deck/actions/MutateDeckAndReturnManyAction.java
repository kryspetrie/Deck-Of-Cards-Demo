package com.kryspetrie.playingcards.deck.actions;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.MutableCardHolder;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Optional;

/** {@link DeckAction} action that mutates the deck and optionally returns cards */
public interface MutateDeckAndReturnManyAction extends DeckAction {

    /**
     * Mutates the deck and optionally returns cards.
     * @param cards {@link MutableCardHolder} containing the cards.
     * @return empty, or a list of cards.
     */
    @Nonnull
    Optional<List<Card>> mutateAndReturnMany(@Nonnull final MutableCardHolder cards);
}
