package com.kryspetrie.playingcards.deck;

import com.kryspetrie.playingcards.deck.actions.DeckAction;
import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.actions.MutateDeckAction;
import com.kryspetrie.playingcards.deck.actions.MutateDeckAndReturnManyAction;
import com.kryspetrie.playingcards.deck.actions.MutateDeckAndReturnOneAction;
import jakarta.annotation.Nonnull;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

/**
 * Test the various deck functions.
 * <p>
 * Note that this test demostrates how easy it is to make {@link DeckAction}s in a functional style.
 */
@ExtendWith(MockitoExtension.class)
class DeckTest {

    @Mock
    private Card mockCardA;

    @Mock
    private Card mockCardB;

    @Test
    void testIsEmptyNoCards() {
        final var deck = new Deck();
        Assertions.assertThat(deck.isEmpty()).isTrue();
    }

    @Test
    void testIsEmptyWithCards() {
        final var deck = new Deck(List.of(mockCardA));
        Assertions.assertThat(deck.isEmpty()).isFalse();
    }

    @Test
    void testGetCardStreamNoCards() {
        // GIVEN we have an empty deck
        final var deck = new Deck();

        // WHEN we ask for a card stream
        // THEN it is an empty stream
        Assertions.assertThat(deck.getCardStream()).isEmpty();
    }

    @Test
    void testGetCardStreamWithCards() {
        // GIVEN we have a deck of cards
        final var deck = new Deck(List.of(mockCardA, mockCardB));

        // WHEN we ask for a card stream
        // THEN it contains exactly those two cards, in order
        Assertions.assertThat(deck.getCardStream()).containsExactly(mockCardA, mockCardB);
    }

    @Test
    void testCountNoCards() {
        // GIVEN we have an empty deck
        final var deck = new Deck();

        // WHEN we ask for a count
        // THEN it is zero
        Assertions.assertThat(deck.count()).isEqualTo(0);
    }

    @Test
    void testCountWithCards() {
        // GIVEN we have an empty deck
        final var deck = new Deck(List.of(mockCardA, mockCardB));

        // WHEN we ask for a count
        // THEN it is two
        Assertions.assertThat(deck.count()).isEqualTo(2);
    }

    @Test
    void testAcceptMutateDeckAction() {
        // GIVEN we have a deck and an action
        final var deck = new Deck(List.of(mockCardA));
        final var action = givenInsertCardAction();

        // THEN we execute the action
        deck.accept(action);

        // THEN the action has been executed
        Assertions.assertThat(deck.getCardStream()).containsExactly(mockCardA, mockCardB);
    }

    @Test
    void testAcceptMutateDeckAndReturnOneAction() {
        // GIVEN we have a deck and an action
        final var deck = new Deck(List.of(mockCardA));
        final var action = givenInsertCardAndPeekFirstCardAction();

        // THEN we execute the action
        final var firstCard = deck.accept(action);

        // THEN the action has been executed
        Assertions.assertThat(deck.getCardStream()).containsExactly(mockCardA, mockCardB);
        Assertions.assertThat(firstCard).isPresent();
        Assertions.assertThat(firstCard.get()).isEqualTo(mockCardA);
    }

    @Test
    void testAcceptMutateDeckAndReturnManyAction() {
        // GIVEN we have a deck and an action
        final var deck = new Deck(List.of(mockCardA));
        final var action = givenInsertCardAndPeekAllAction();

        // THEN we execute the action
        final var allCards = deck.accept(action);

        // THEN the action has been executed
        Assertions.assertThat(deck.getCardStream()).containsExactly(mockCardA, mockCardB);
        Assertions.assertThat(allCards).isPresent();
        Assertions.assertThat(allCards.get()).containsExactly(mockCardA, mockCardB);
        Assertions.assertThat(allCards.get()).isNotExactlyInstanceOf(MutableCardHolder.class);
    }

    @Nonnull
    private MutateDeckAction givenInsertCardAction() {
        return cards -> cards.addLast(mockCardB);
    }

    @Nonnull
    private MutateDeckAndReturnOneAction givenInsertCardAndPeekFirstCardAction() {
        return cards -> {
            cards.addLast(mockCardB);
            return cards.isEmpty() ?
                    Optional.empty() :
                    Optional.of(cards.getFirst());
        };
    }

    @Nonnull
    private MutateDeckAndReturnManyAction givenInsertCardAndPeekAllAction() {
        return cards -> {
            cards.addLast(mockCardB);
            return Optional.of(List.copyOf(cards));
        };
    }

}