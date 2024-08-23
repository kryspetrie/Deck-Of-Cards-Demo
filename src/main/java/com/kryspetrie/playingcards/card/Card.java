package com.kryspetrie.playingcards.card;

import jakarta.annotation.Nonnull;

/**
 * A generic card with a name identifier.
 * All cards should implement this interface.
 */
public interface Card {

    /**
     * Returns an identifier for the card.
     * <p>
     * This is intended more as an example than anything,
     * as the UI (or language-dependent lookup) would be responsible for determining the
     * representation of this card to the user. It is here for convenience.
     * @return identifier for the card.
     **/
    @Nonnull
    String getName();
}
