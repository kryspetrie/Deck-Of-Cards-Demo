package com.kryspetrie.playingcards.deck.actions;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.MutableCardHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DrawOneDeckActionTest {

    @Mock
    private MutableCardHolder mockMutableCardHolder;

    @Mock
    private Optional<Card> mockOptionalCard;

    private final MutateDeckAndReturnOneAction deckAction = new DrawOneDeckAction();

    @Test
    void testDrawOne() {
        // GIVEN we can return an optional
        Mockito.when(mockMutableCardHolder.takeFirst()).thenReturn(mockOptionalCard);

        // WHEN we ask to draw one card
        var optionalCard = deckAction.mutateAndReturnOne(mockMutableCardHolder);

        // THEN we return the optional card
        Assertions.assertThat(optionalCard).isEqualTo(mockOptionalCard);

        // THEN we verify we called the method
        Mockito.verify(mockMutableCardHolder).takeFirst();
    }
}