package com.kryspetrie.playingcards;

import com.kryspetrie.playingcards.card.Card;
import com.kryspetrie.playingcards.deck.Deck;
import com.kryspetrie.playingcards.deck.actions.ShuffleDeckAction;
import com.kryspetrie.playingcards.deck.providers.FourDeckStandardFrenchDeckProvider;
import com.kryspetrie.playingcards.deck.providers.StandardFrenchDeckWithJokersProvider;
import com.kryspetrie.playingcards.utils.RandomUtils;
import com.kryspetrie.playingcards.deck.actions.DrawOneDeckAction;
import com.kryspetrie.playingcards.deck.actions.DrawSevenDeckAction;
import com.kryspetrie.playingcards.deck.inspection.CardNamesDeckInspection;
import com.kryspetrie.playingcards.deck.providers.StandardFrenchDeckProvider;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Demo Application by Krys Petrie (Aug 9, 2024)
 * <p>
 * This would not be included in the final product, and is for demo purposes only.
 * <p>
 * This is not intended as a test of this library. Unit Tests have been written.
 * <p>
 * I did not assume this would be a web app, so I did not build out endpoints.
 * I also did not assume an IoC framework, so I did not include Guice, etc.
 */
public class Main {

    private static final String EMPTY_SIGNIFIER = "==EMPTY==";
    private static final String NONE_SIGNIFIER = "==NONE==";

    /**
     * For a basic demo of this library, do not pass any arguments.
     * Pass ANY argument to demo some additional features provided by the library.
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            // Demo the basic interface defined by the problem statement
            System.out.println("\nBASIC FEATURES DEMO\n");
            demoBasicFeatures();
            return;
        }

        // Demo some additional of this library
        System.out.println("\nADDITIONAL FEATURES DEMO\n");
        demoAdditionalFeatures();
    }

    /** Demo the basic interface, as requested by the problem statement */
    private static void demoBasicFeatures() {

        // Initialize the basic service.
        // In a real application, this would likely be done via an IoC injector.
        final var service = new FrenchStandardDeckService(
                new StandardFrenchDeckProvider(),
                new ShuffleDeckAction(new RandomUtils(new Random())),
                new DrawOneDeckAction());

        // Get a standard french deck
        final var deck = service.provideDeck();
        printDeckWithHeader("Initial state of the deck:", deck);

        // Shuffle the deck
        service.shuffle(deck);
        printDeckWithHeader("Deck after shuffle:", deck);

        // Draw all the cards from the deck, one by one
        printHeader("Drawing all cards from the deck, one by one...");
        do {
            final var card = service.drawOne(deck);
            printDraw(card.orElse(null));
        } while (!deck.isEmpty());

        // Try to draw one more card from the empty deck
        printHeader("Trying to draw one more card...");
        final var finalDraw = service.drawOne(deck);
        printDraw(finalDraw.orElse(null));

        // Final state of the deck
        printDeckWithHeader("Final State of the deck:", deck);
    }

    /** Demos some other bits of the underlying library, outside the scope of the specific ask. */
    private static void demoAdditionalFeatures() {

        // Initialize some other aspects of this library
        final var frenchDeckWithJokersProvider = new StandardFrenchDeckWithJokersProvider();
        final var fourDeckStandardFrenchDeckProvider = new FourDeckStandardFrenchDeckProvider();
        final var shuffleDeckAction = new ShuffleDeckAction(new RandomUtils(new Random()));
        final var cardNamesDeckInspection = new CardNamesDeckInspection();
        final var drawOneDeckAction = new DrawOneDeckAction();
        final var drawSevenDeckAction = new DrawSevenDeckAction();

        // Acquire a standard deck INCLUDING jokers
        final var deck = frenchDeckWithJokersProvider.provideDeck();

        // Inspect the shuffled deck using DeckInspection interface
        final List<String> cardsNamesBeforeShuffle = cardNamesDeckInspection.inspect(deck);

        // Shuffle the deck
        deck.accept(shuffleDeckAction);

        // Inspect the shuffled deck
        final List<String> cardsBeforeDraws = cardNamesDeckInspection.inspect(deck);

        // Draw 1 card from the deck
        final var firstCard = deck.accept(drawOneDeckAction);

        // Draw 7 cards from the deck
        final var nextSevenCards = deck.accept(drawSevenDeckAction);

        // Inspect deck after we have drawn 8 cards
        final var cardsAfterDraws = cardNamesDeckInspection.inspect(deck);

        // Get a deck with 4 decks worth of cards
        final var bigDeck = fourDeckStandardFrenchDeckProvider.provideDeck();

        // Count the cards in the big deck
        final var bigDeckCount = bigDeck.count();

        // Print what we just did
        printCardNamesWithHeader("Deck before shuffle:", cardsNamesBeforeShuffle);
        printCardNamesWithHeader("Deck after shuffle:", cardsBeforeDraws);
        printCard("Drawing one card:", firstCard.orElse(null));
        printCardsOneLine("Drawing seven cards:", nextSevenCards.orElse(null));
        printCardsWithToString("Those seven cards, as full objects:", nextSevenCards.orElse(null));
        printCardNamesWithHeader("Final state of the deck:", cardsAfterDraws);
        System.out.println("\nAcquired a big deck (4 decks, combined). Card Count: " + bigDeckCount);
    }

    private static void printCardsOneLine(@Nonnull String title, @Nullable List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            System.out.println(title + " " + EMPTY_SIGNIFIER);
            return;
        }

        final String joined = cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));

        System.out.println(title + " [" + joined + "]");
    }

    private static void printCard(@Nonnull final String title, @Nullable final Card card) {
        if (card == null) {
            System.out.println(title + " " + NONE_SIGNIFIER);
            return;
        }

        System.out.println(title + " " + card.getName());
    }

    private static void printDeckWithHeader(@Nonnull final String header, @Nonnull final Deck deck) {
        printHeader(header);
        if (deck.isEmpty()) {
            System.out.println(EMPTY_SIGNIFIER);
            return;
        }

        final String joined = deck.getCardStream()
                .map(Card::getName)
                .collect(Collectors.joining("\n"));
        System.out.println(joined);
    }

    private static void printCardNamesWithHeader(@Nonnull final String header, @Nullable final List<String> cardNames) {
        printHeader(header);

        if (cardNames == null || cardNames.isEmpty()) {
            System.out.println(EMPTY_SIGNIFIER);
            return;
        }

        final String joined = String.join("\n", cardNames);
        System.out.println(joined);
    }

    private static void printHeader(String header) {
        System.out.println("\n" + header + "\n=====================================");
    }

    private static void printDraw(@Nullable final Card card) {
        if (card == null) {
            System.out.println("Drew: " + NONE_SIGNIFIER);
            return;
        }

        System.out.println("Drew: " + card.getName());
    }

    private static void printCardsWithToString(@Nonnull final String header, @Nullable final List<Card> cards) {
        printHeader(header);
        if (cards == null || cards.isEmpty()) {
            System.out.println(EMPTY_SIGNIFIER);
            return;
        }

        for (final Card card : cards) {
            System.out.println(card);
        }
    }
}