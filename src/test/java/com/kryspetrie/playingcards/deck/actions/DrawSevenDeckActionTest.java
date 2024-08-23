package com.kryspetrie.playingcards.deck.actions;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.MutableCardHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class DrawSevenDeckActionTest {

    @Mock
    private Card mockCard;

    @Mock
    private MutableCardHolder mockMutableCardHolder;

    private final MutateDeckAndReturnManyAction deckAction = new DrawSevenDeckAction();

    @Test
    void testDrawSevenWithCardsResponse() {
        // GIVEN we can draw some number of cards
        Mockito.when(mockMutableCardHolder.takeFirst(7)).thenReturn(List.of(mockCard));

        // WHEN we ask for the draw
        final var optionalCards = deckAction.mutateAndReturnMany(mockMutableCardHolder);

        // THEN we got the expected list
        Assertions.assertThat(optionalCards).isPresent();
        Assertions.assertThat(optionalCards.get()).containsExactly(mockCard);

        // THEN we called the cardholder with the expected arguments
        Mockito.verify(mockMutableCardHolder).takeFirst(7);
    }

    @Test
    void testDrawSevenWithNoCardsResponse() {
        // GIVEN there are not any cards to draw
        Mockito.when(mockMutableCardHolder.takeFirst(7)).thenReturn(Collections.emptyList());

        // WHEN we ask for the draw
        final var optionalCards = deckAction.mutateAndReturnMany(mockMutableCardHolder);

        // THEN we got an empty result
        Assertions.assertThat(optionalCards).isEmpty();

        // THEN we called the cardholder with the expected arguments
        Mockito.verify(mockMutableCardHolder).takeFirst(7);
    }
}