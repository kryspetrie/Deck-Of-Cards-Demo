package com.kryspetrie.playingcards.deck;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.actions.DeckAction;
import com.kryspetrie.playingcards.deck.actions.MutateDeckAction;
import com.kryspetrie.playingcards.deck.actions.MutateDeckAndReturnManyAction;
import com.kryspetrie.playingcards.deck.actions.MutateDeckAndReturnOneAction;
import jakarta.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A Deck containing list of {@link Card}.
 * <p>
 * Deck mutation must occur via an appropriate {@link DeckAction}.
 * Do NOT include a direct accessor to the internal cards representation.
 */
@EqualsAndHashCode
@ToString
public class Deck {

    @Nonnull
    private final MutableCardHolder cards;

    /**
     * Builds a {@link Deck} from an existing collection of cards.
     * @param initialCards Collection of {@link Card} used to populate this deck.
     */
    public Deck(@Nonnull final Collection<Card> initialCards) {
        this.cards = new MutableCardHolder(initialCards);
    }

    /**
     * Builds an empty {@link Deck}.
     */
    public Deck() {
        this.cards = new MutableCardHolder();
    }

    /**
     * Return a stream of the cards in the deck, top to bottom.
     * @return Stream of cards.
     */
    public Stream<Card> getCardStream() {
        return cards.stream();
    }

    /**
     * Return the number of cards currently in the deck.
     * @return the count of cards.
     */
    public int count() {
       return cards.size();
    }

    /** Returns true if no cards are in the deck */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Accepts a {@link DeckAction} to mutate the deck.
     * @param action {@link MutateDeckAction} used to mutate the deck.
     */
    public void accept(@Nonnull final MutateDeckAction action) {
        action.mutate(cards);
    }

    /**
     * Accepts a {@link DeckAction} to mutate the deck, optionally returning a card.
     * @param action {@link MutateDeckAndReturnOneAction} used to mutate the deck and optionally return a card.
     * @return Optional {@link Card} as returned by the {@link DeckAction}
     */
    public Optional<Card> accept(@Nonnull final MutateDeckAndReturnOneAction action) {
        return action.mutateAndReturnOne(cards);
    }

    /**
     * Accepts a {@link DeckAction} to mutate the deck, optionally returning cards.
     * @param action {@link MutateDeckAndReturnManyAction} used to mutate the deck and optionally return cards.
     * @return Optional list of {@link Card} as returned by the {@link DeckAction}
     */
    public Optional<List<Card>> accept(@Nonnull final MutateDeckAndReturnManyAction action) {
        return action.mutateAndReturnMany(cards);
    }
}
