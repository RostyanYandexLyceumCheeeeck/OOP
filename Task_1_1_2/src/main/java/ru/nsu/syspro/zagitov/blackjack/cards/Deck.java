package ru.nsu.syspro.zagitov.blackjack.cards;

import static ru.nsu.syspro.zagitov.blackjack.Constants.deckSumMinLimit;
import static ru.nsu.syspro.zagitov.blackjack.Constants.numberOfCards;
import static ru.nsu.syspro.zagitov.blackjack.Constants.sumOneDeck;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class Deck.
 */
public class Deck {
    private long sumDeck;
    protected final int countDecks;
    protected ArrayList<Card> cards = null;

    /**
     * create instance of the class.
     *
     * @param newCountDecks count decks card.
     */
    public Deck(int newCountDecks) throws Exception {
        countDecks = newCountDecks;
        setCards();
    }

    /**
     * initialize {@code cards} and shuffle.
     */
    protected void setCards() throws Exception {
        cards = new ArrayList<>(this.countDecks * numberOfCards);
        for (int i = 0; i < this.countDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(rank.getName(), suit.getName(), true));
                }
            }
            sumDeck = (long) sumOneDeck * this.countDecks;
        }
        Collections.shuffle(cards);
    }

    /**
     * issues a card and removes it from the deck.
     *
     * @return card.
     */
    public Card getCard() throws Exception {
        if (sumDeck < deckSumMinLimit) {
            setCards();
        }
        Card result = cards.remove(cards.size() - 1);
        sumDeck -= (result.getPrice() == Rank.ACE.points ? 1 : result.getPrice());
        return result;
    }
}