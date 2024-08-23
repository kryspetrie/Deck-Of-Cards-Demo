package com.kryspetrie.playingcards.deck.actions;


import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.card.TestCardConstants;
import com.kryspetrie.playingcards.deck.Deck;
import com.kryspetrie.playingcards.utils.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ShuffleDeckActionTest {

    @Mock
    private RandomUtils randomUtils;

    private MutateDeckAction deckAction;

    @BeforeEach
    void setUp() {
        // @InjectMocks is buggy in this version of Mockito
        this.deckAction = new ShuffleDeckAction(randomUtils);
    }

    @Test
    void testShuffle() {
        // GIVEN we have a deck with 5 cards
        final Deck deck = new Deck(List.of(TestCardConstants.CARD_A, TestCardConstants.CARD_B, TestCardConstants.CARD_C, TestCardConstants.CARD_D, TestCardConstants.CARD_E));
        givenRandomReturns(4, 2);
        givenRandomReturns(3, 0);
        givenRandomReturns(2, 2);
        givenRandomReturns(1, 1);

        // WHEN we shuffle the deck
        deck.accept(deckAction);

        // THEN the deck returns in the expected order
        Assertions.assertThat(deck.getCardStream())
                .as("Shuffle should return the expected order")
                .containsExactly(TestCardConstants.CARD_D, TestCardConstants.CARD_B, TestCardConstants.CARD_E, TestCardConstants.CARD_A, TestCardConstants.CARD_C);
    }

    @Test
    void testShuffleMultiple() {
        // GIVEN we have a deck of five cards
        final List<Card> startingOrder = List.of(TestCardConstants.CARD_A, TestCardConstants.CARD_B, TestCardConstants.CARD_C, TestCardConstants.CARD_D, TestCardConstants.CARD_E);
        final Deck deck = new Deck(startingOrder);
        givenRandomReturns(4, List.of(4, 2, 3));
        givenRandomReturns(3, List.of(0, 1, 2));
        givenRandomReturns(2, List.of(1, 0, 2));
        givenRandomReturns(1, List.of(1, 0, 1));

        // WHEN we shuffle the deck
        deck.accept(deckAction);
        final List<Card> afterFirstShuffle = deck.getCardStream().toList();

        // WHEN we shuffle it again
        deck.accept(deckAction);
        final List<Card> afterSecondShuffle = deck.getCardStream().toList();

        // WHEN we shuffle it again
        deck.accept(deckAction);
        final List<Card> afterThirdShuffle = deck.getCardStream().toList();

        // THEN the first shuffle changed the order
        Assertions.assertThat(afterFirstShuffle)
                .as("First shuffle should have changed order")
                .doesNotContainSequence(startingOrder)
                .containsAll(startingOrder);


        // THEN the second shuffle changed the order again
        Assertions.assertThat(afterSecondShuffle)
                .as("Second shuffle should have changed order")
                .doesNotContainSequence(afterFirstShuffle)
                .containsAll(startingOrder);

        // THEN the third shuffle changed the order again
        Assertions.assertThat(afterThirdShuffle)
                .as("Third shuffle should have changed order")
                .doesNotContainSequence(afterSecondShuffle)
                .containsAll(startingOrder);
    }

    private void givenRandomReturns(final int upperBound, final int returnValue) {
        Mockito.when(randomUtils.getRandomIntegerWithinRangeInclusive(0, upperBound))
                .thenReturn(returnValue);
    }

    private void givenRandomReturns(final int upperBound, final List<Integer> returnValues) {
        Integer first = returnValues.getFirst();
        Integer[] rest = returnValues.subList(1, returnValues.size()).toArray(new Integer[0]);
        Mockito.when(randomUtils.getRandomIntegerWithinRangeInclusive(0, upperBound))
                .thenReturn(first, rest);
    }
}