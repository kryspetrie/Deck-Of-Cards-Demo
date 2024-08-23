package com.kryspetrie.playingcards;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.Deck;
import com.kryspetrie.playingcards.deck.actions.MutateDeckAction;
import com.kryspetrie.playingcards.deck.actions.MutateDeckAndReturnOneAction;
import com.kryspetrie.playingcards.deck.providers.DeckProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FrenchStandardDeckServiceTest {

    @Mock
    private Deck mockDeck;

    @Mock
    private Card mockCard;

    @Mock
    private DeckProvider mockDeckProvider;

    @Mock
    private MutateDeckAction mockShuffleAction;

    @Mock
    private MutateDeckAndReturnOneAction mockDealCardAction;

    private FrenchStandardDeckService standardDeckService;

    @BeforeEach
    void setUp() {
        // @InjectMocks is buggy in this version of Mockito
        this.standardDeckService = new FrenchStandardDeckService(mockDeckProvider, mockShuffleAction, mockDealCardAction);
    }

    @Test
    void testDeckProvider() {
        // GIVEN we can get a deck
        givenWeCanGetADeck();

        // WHEN we ask for a deck
        final var deck = standardDeckService.provideDeck();

        // THEN we got it
        Assertions.assertThat(deck).isEqualTo(mockDeck);
    }

    @Test
    void testShuffle() {
        // WHEN we ask to shuffle the deck
        standardDeckService.shuffle(mockDeck);

        // THEN verify we shuffled the deck
        Mockito.verify(mockDeck).accept(mockShuffleAction);
    }

    @Test
    void testDealReturnsCard() {
        // GIVEN a deck
        givenWeCanDealCards();

        // WHEN we deal a card
        final var card = standardDeckService.drawOne(mockDeck);

        // THEN we got the expected card
        Assertions.assertThat(card).isPresent();
        Assertions.assertThat(card.get()).isEqualTo(mockCard);
    }

    @Test
    void testDealReturnsEmpty() {
        // WHEN we deal a card
        final var card = standardDeckService.drawOne(mockDeck);

        // THEN we got the expected card
        Assertions.assertThat(card).isEmpty();
    }

    private void givenWeCanDealCards() {
        Mockito.when(mockDeck.accept(mockDealCardAction)).thenReturn(Optional.of(mockCard));
    }

    private void givenWeCanGetADeck() {
        Mockito.when(mockDeckProvider.provideDeck()).thenReturn(mockDeck);
    }
}