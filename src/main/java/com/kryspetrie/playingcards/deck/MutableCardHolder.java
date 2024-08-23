package com.kryspetrie.playingcards.deck;

import com.kryspetrie.playingcards.card.Card;

import java.util.*;
import java.util.stream.Stream;

/**
 * Mutable container holding cards. Extends {@link ArrayList} for convenience.
 * <p>
 * The intention is to externalize deck mutation as much as possible,
 * with common convenience methods being added here.
 */
public class MutableCardHolder extends ArrayList<Card> {

    /**
     * Builds a {@link MutableCardHolder} from an existing collection.
     * @param cards Collection of {@link Card} used to populate this container.
     */
    public MutableCardHolder(Collection<? extends Card> cards) {
        super(cards);
    }

    /**
     * Build an empty {@link MutableCardHolder}
     */
    public MutableCardHolder() {
    }

    /**
     * Swaps cards in the deck by index (0-index).
     * Invalid indexes will throw {@link IllegalArgumentException}
     * @param indexA Index of first card to swap.
     * @param indexB Index of second card to swap.
     */
    public void swapCardsByIndex(int indexA, int indexB) {
        final int lastIndex = this.size() - 1;
        if (indexA < 0 || indexB < 0 || indexA > lastIndex || indexB > lastIndex) {
            throw new IllegalArgumentException("Indexes must be within range.");
        }

        // Same index; Nothing to do
        if (indexA == indexB) {
            return;
        }

        // Swap the cards
        final Card cardA = this.get(indexA);
        final Card cardB = this.get(indexB);
        this.set(indexA, cardB);
        this.set(indexB, cardA);
    }

    /**
     * Take the top card from the deck, reducing deck size by one.
     * @return optional first {@link Card} from the deck, or empty
     */
    public Optional<Card> takeFirst() {
        if (this.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(this.removeFirst());
    }

    /**
     * Take the top-n cards from the deck, reducing the deck size by n.
     * Throws {@link IllegalArgumentException} for invalid arguments.
     * @param numberOfCards Number of cards to remove.
     * @return the top-n {@link Card}s from the deck.
     */
    public List<Card> takeFirst(final int numberOfCards) {
        if (numberOfCards <= 0) {
            throw new IllegalArgumentException("Must request 1 or more cards.");
        }

        final int validCardsToRemove = Math.min(numberOfCards, this.size());

        // No cards to remove
        if (validCardsToRemove == 0) {
            return Collections.emptyList();
        }

        // Remove and return that number of cards
        return Stream.generate(this::removeFirst)
                .limit(validCardsToRemove)
                .toList();
    }
}
