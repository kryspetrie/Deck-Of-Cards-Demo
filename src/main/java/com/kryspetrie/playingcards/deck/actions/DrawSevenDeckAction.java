package com.kryspetrie.playingcards.deck.actions;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.MutableCardHolder;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Optional;

/** Example of another type of action to draw 7 cards from the top of the deck. */
public class DrawSevenDeckAction implements MutateDeckAndReturnManyAction {

    /**
     * Return the top 7 cards from the deck, or empty.
     * If there are fewer than 7 cards, return available cards.
     * @param cards {@link MutableCardHolder} containing the cards.
     * @return optionally return up to 7 cards.
     */
    @Nonnull
    @Override
    public Optional<List<Card>> mutateAndReturnMany(@Nonnull final MutableCardHolder cards) {
        final List<Card> topCards = cards.takeFirst(7);
        return topCards.isEmpty() ? Optional.empty() : Optional.of(topCards);
    }
}
