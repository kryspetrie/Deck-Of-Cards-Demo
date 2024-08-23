package com.kryspetrie.playingcards.card.standardfrench;

import com.kryspetrie.playingcards.card.Card;
import jakarta.annotation.Nonnull;
import lombok.*;

/**
 * Represents a standard deck card with the
 * <a href="https://en.wikipedia.org/wiki/French-suited_playing_cards">"French"</a>
 * colors and suits.
 * <p>
 * This card contains attributes one would expect to use during gameplay with this type of deck.
 * The numeric / hierarchical value during gameplay (e.g. Ace is both 1 and trumps King) is
 * game dependent, so it is not included in this model.
 * <p>
 * Note: Java "record" classes cannot have package private constructors.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class FrenchSuitCard implements Card {

    @Nonnull
    String name;

    @Nonnull
    FrenchSuit suit;

    @Nonnull
    FrenchType type;

    @Nonnull
    FrenchColor color;
}
