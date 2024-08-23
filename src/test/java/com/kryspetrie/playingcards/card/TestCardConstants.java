package com.kryspetrie.playingcards.card;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** Use these cards in place of a "French Card" where you just need a generic card. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestCardConstants {
    public static final Card CARD_A = new TestCard("Card A");
    public static final Card CARD_B = new TestCard("Card B");
    public static final Card CARD_C = new TestCard("Card C");
    public static final Card CARD_D = new TestCard("Card D");
    public static final Card CARD_E = new TestCard("Card E");
}
