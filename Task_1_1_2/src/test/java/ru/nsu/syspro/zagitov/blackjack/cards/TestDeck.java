package ru.nsu.syspro.zagitov.blackjack.cards;

import static ru.nsu.syspro.zagitov.blackjack.Constants.closeCard;
import static ru.nsu.syspro.zagitov.blackjack.Constants.numberOfCards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * testing class Deck.
 */
public class TestDeck {
    @Test
    public void setCards() throws Exception {
        int countDecks = 1;
        Deck deck = new Deck(countDecks);
        Assertions.assertEquals(countDecks * numberOfCards, deck.cards.size());
    }

    @Test
    public void getCard() throws Exception {
        int countDecks = 1;
        Deck deck = new Deck(countDecks);
        Assertions.assertEquals(deck.cards.get(deck.cards.size() - 1), deck.getCard());
    }

    @Test
    public void getCardRemove() throws Exception {
        int countDecks = 1;
        Deck deck = new Deck(countDecks);
        deck.getCard();
        Assertions.assertEquals(countDecks * numberOfCards - 1, deck.cards.size());
    }

}
