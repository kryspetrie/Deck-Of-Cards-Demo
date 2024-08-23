package com.kryspetrie.playingcards.deck.actions;

import com.kryspetrie.playingcards.utils.RandomUtils;
import com.kryspetrie.playingcards.deck.MutableCardHolder;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

/**
 * Deck Action to shuffle a deck of cards using the modern version of the
 * <a href=https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle>Fisher Yates Shuffle</a>
 * algorithm by Richard Durstenfeld.
 * <p>
 * This algorithm does a single pass over the cards, swapping cards in place.
 * During each iteration, it works on a decreasing subset of the cards, starting with the full set.
 * A card is chosen at random within the index 0-n window, with n being the current window size.
 * This card is then swapped with the current last card within the window.
 * The window is decreased, and this repeats until the window is empty.
 */
@RequiredArgsConstructor
public class ShuffleDeckAction implements MutateDeckAction {

    private final RandomUtils randomUtils;

    /**
     * Shuffles the deck in-place using a modified Fisher-Yates shuffle.
     * @param cards {@link MutableCardHolder} containing cards.
     */
    @Override
    public void mutate(@Nonnull final MutableCardHolder cards) {
        final int cardsInDeck = cards.size();

        // If we have one card, nothing to do.
        if (cardsInDeck == 1) {
            return;
        }

        // Shuffle the cards in place, using modified algorithm.
        for (int lastIdx = cardsInDeck - 1; lastIdx > 0; lastIdx--) {

            // Choose a number between first and last index, inclusive
            final int randIdx = randomUtils.getRandomIntegerWithinRangeInclusive(0, lastIdx);

            // If the indexes are the same, nothing to do
            if (lastIdx == randIdx) {
                continue;
            }

            // Swap the cards
            cards.swapCardsByIndex(lastIdx, randIdx);
        }
    }
}
