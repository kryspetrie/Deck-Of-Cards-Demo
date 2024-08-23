## Deck of Cards Demo
This application was written as part of a take-home programming problem on August 9, 2024.

### Problem Statement
Build a component that represents a standard 52-card deck of cards, for use in a production application. 
* Build a representation of cards and a deck, assuming a standard 52-card deck.
* This deck should be able to be shuffled. e.g. Provide "shuffle()" method
  * You may not use a shuffle library method
* You should be able to draw a card from the deck. e.g. Provide "dealCard()" method
  * This removes a card from the deck and returns it
  * You must handle the case when the deck becomes empty

### Design Goals
* Build a library flexible enough to be used generically in different types of game libraries.
  * Do not make the assumption that the "standard 52 card deck" is the only type of card deck
  * Provide core mechanisms for manipulate a deck and cards, without being tied to a card type
  * Hide implementation specifics enough to maintain a coherent interaction model with the cards
  * Ensure that core functions can be extended in a consistent way by additional libraries and add-ons
  * Provide a straightforward interface that does not stray far from OOD expectations
  * Do not assume any specific frontend use cases; this is a backend representation.
* Build a library that is of production quality
* Exhaustively test all functions of this library
* Provide a simple "main" class to demo the functionality using a simple CLI

### Design Overview

#### Cards
A `Card` interface represents a generic card.

One such implementation provided here is for a "standard french" deck, which is the red and black version of the 52-card standard deck.
This card, defined as a `FrenchSuitCard`, contains a name (e.g. "Ace of Spades"), suit (`FrenchSuit`, e.g. "Spades"), type (`FrenchType`, e.g. "Ace"), and color (`FrenchColor`, e.g. "Black").
Aside from "name", these fields are all enum values. Constants are defined for all valid card types in `FrenchSuitCardConstants` with groupings of cards defined in `FrenchSuitCardGroupConstants`.

Within this application, there is no game logic. As such, the only part of the `FrenchSuitCard` that comes into play is the "name" field.
For convenience, this was added to the `Card` interface so we can easily demonstrate dealing and sorting functionality without casting back to `FrenchSuitCard`.

#### Deck
A `Deck` class represents a deck containing any type of `Card`.

This deck class exposes a stream of `Card` and exposes basic data like if the deck is empty and the count of cards in the deck.

Deck mutation is not allowed directly via the `Deck` interface, but must be accomplished by a `Deck Action` (described below), passed into the appropriate `Deck` mutator method.
These mutator implementations are given access to the underlying card storage within a deck, the `MutableCardHolder`. This separation was made to clearly separate deck manipulation from the core `Deck` object. 
This way, new game engines can define their own custom `DeckAction` external to this package, to manipulate the deck.

#### Deck Actions
A `DeckAction` is a marker interface, extended by various other interfaces, defining different classes of interactions with a deck.
All interfaces extending `DeckAction` currently provide access to the deck mutation via the `MutableCardHolder`.

The `MutateDeckAction` interface defines action that mutate the deck but do not return cards. 
The only implementation here is `ShuffleDeckAction`, which shuffles the deck using a modified "[Fisher Yates Shuffle](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle)".

The `MutateDeckAndReturnOneAction` interface defines an action that mutates the deck and returns exactly one card. 
The only implementation here is `DrawOneDeckAction`, which deals one card from the deck.

The `MutateDeckAndReturnManyAction` interface is an example extension of the core functionality, handling cases where multiple cards need to be returned from an action.
The only implementation here is `DrawSevenDeckAction`, which simply draws 7 cards from the deck. 
Other implementations could describe much more complex behaviors than this, however.

#### Deck Inspections
A `DeckInspection` is an interface that defines a standardized way to look through cards in the deck.
This was an example of another way to extend this application, for specific needs by the future game engine.

In the example here, `CardNamesDeckInspection` simply returns a list of the names of all cards in the deck.

#### Deck Providers
A `DeckProvider` is an interface defining the method to create some type of `Deck`, containing generic `Card` entities.

The `StandardFrenchDeckProvider` implementation simply returns a new, ordered, standard "French" deck containing 52 cards.
The `StandardFrenchDeckWithJokersProvider` and `FourDeckStandardFrenchDeckProvider` implementations are other examples
of deck providers you might find implementing this interface.

### Tests
This application was extensively tested using Unit Tests. 

It leverages Mockito for mocking, as needed, as well as Test Doubles, for generic objects such as `Card`,
to ensure that tests are not strictly tied to that the one implemented card type.

Also, the `assertj` assertions library was used in place of standard `jupiter` assertions, because it has
a more fully featured interface, supporting a fluent style.