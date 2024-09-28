package ru.nsu.syspro.zagitov.blackjack;

import ru.nsu.syspro.zagitov.blackjack.cards.Card;

import static ru.nsu.syspro.zagitov.blackjack.Constants.blackJack;

import java.util.ArrayList;


/**
 * class Player.
 */
public class Player {
    private int score = 0;
    private final ArrayList<Card> hand;

    /**
     * create instance of the class.
     */
    public Player() {
        hand = new ArrayList<>();
    }

    /**
     * setup {@code hand} and update {@code score}.
     */
    private void setHand() {
        for (Card card : hand) {
            if (score < blackJack) {
                return;
            }
            int oldPrice = card.getPrice();
            card.setOverflow();
            score -= oldPrice - card.getPrice();
        }
    }

    /**
     * add card in hand.
     *
     * @param card card (instance of the class Card).
     * @return true if {@code score > blackJack} else false.
     */
    public boolean addCard(Card card) {
        hand.add(card);
        score += card.getPrice();
        if (score > blackJack) {
            setHand();
        }
        return (score > blackJack);
    }

    /**
     * get score hand.
     *
     * @return {@code score}.
     */
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        boolean close = false;

        for (Card card : hand) {
            result.append(card.toString()).append(", ");
            close = close || !card.faceUp;
        }
        result = new StringBuilder(result.substring(0, result.length() - 2) + "]");

        if (close) {
            return result.toString();
        }
        return result + " ==> " + score;
    }
}
