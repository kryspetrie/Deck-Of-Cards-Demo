package com.kryspetrie.playingcards.card;

import jakarta.annotation.Nonnull;
import lombok.Value;

/**
 * The purpose of this class is to decouple tests from the "French Cards" whenever possible
 */
@Value
public class TestCard implements Card {
    @Nonnull
    String name;
}
