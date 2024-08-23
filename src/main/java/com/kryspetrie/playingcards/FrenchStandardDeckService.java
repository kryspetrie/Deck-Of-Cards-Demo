package com.kryspetrie.playingcards;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.Deck;
import com.kryspetrie.playingcards.deck.actions.MutateDeckAction;
import com.kryspetrie.playingcards.deck.actions.MutateDeckAndReturnOneAction;
import com.kryspetrie.playingcards.deck.providers.DeckProvider;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/** Simplistic service adhering to the external interface provided by the problem statement.
 * <p>
 * Note: I took slight liberties with the methods contract to pass in the deck object,
 * and to adhere to the Java standard naming conventions.
 */
@RequiredArgsConstructor
public class FrenchStandardDeckService {

    private final DeckProvider deckProvider;
    private final MutateDeckAction shuffleAction;
    private final MutateDeckAndReturnOneAction drawOneAction;

    /**
     * Returns an unshuffled 52-card standard "French" deck.
     * @return {@link Deck} with french cards.
     */
    @Nonnull
    public Deck provideDeck() {
        return deckProvider.provideDeck();
    }

    /**
     * Shuffles the provided deck, in place.
     * @param deck {@link Deck} to shuffle
     */
    public void shuffle(@Nonnull Deck deck) {
        deck.accept(shuffleAction);
    }

    /**
     * Draws one card from the top of the deck, returning it.
     * @param deck {@link Deck} holding the cards
     * @return the top {@link Card} from the deck, or empty if not cards left.
     */
    public Optional<Card> drawOne(@Nonnull Deck deck) {
        return deck.accept(drawOneAction);
    }
}
