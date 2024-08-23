package com.kryspetrie.playingcards.deck.inspection;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.Deck;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * An example of a different type of interaction upon a deck.
 * Inspects a deck and returns the identifiers of the cards in the deck, in deck order.*/
public class CardNamesDeckInspection implements DeckInspection<List<String>> {
    @Override
    public List<String> inspect(@Nonnull final Deck deck) {
        return deck.getCardStream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }
}
