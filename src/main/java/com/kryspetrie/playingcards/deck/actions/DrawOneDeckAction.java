package com.kryspetrie.playingcards.deck.actions;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.MutableCardHolder;
import jakarta.annotation.Nonnull;

import java.util.Optional;

/** Draws one card from the top of the deck, returning the card. Simple pass-through. */
public class DrawOneDeckAction implements MutateDeckAndReturnOneAction {

    /**
     * Draws one card from the top of the deck, or empty.
     * @param cards {@link MutableCardHolder} containing cards.
     * @return the first {@link Card} from the top of the deck, or empty.
     */
    @Nonnull
    @Override
    public Optional<Card> mutateAndReturnOne(@Nonnull final MutableCardHolder cards) {
        return cards.takeFirst();
    }
}
