package ru.nsu.syspro.zagitov.blackjack.cards;

import static ru.nsu.syspro.zagitov.blackjack.Constants.closeCard;
import static ru.nsu.syspro.zagitov.blackjack.Constants.overflowAce;


/**
 * Class card.
 */
public class Card {
    private final Rank rank;
    private final Suit suit;
    private int overflow;
    public boolean faceUp;

    /**
     * create instance of the class.
     *
     * @param rank is string name card.
     * @param suit is string surname card.
     * @param isFaceUp shows if the card is turned over.
     */
    public Card(String rank, String suit, boolean isFaceUp)  throws Exception {

        try {
            this.rank = Rank.findByName(rank);
            this.suit = Suit.findByName(suit);
        } catch (IllegalArgumentException e) {
            throw new Exception("Wrong card!");
        }
        this.overflow = 0;
        this.faceUp = isFaceUp;
    }

    /**
     * setup overflow. if card is Ace, this.overflow = -10.
     * */
    public void setOverflow() {
        if (rank == Rank.ACE) {
            this.overflow = -overflowAce;
        }
    }

    /**
     * reset overflow.
     */
    public void resetOverflow() {
        this.overflow = 0;
    }

    /**
     * get overflow.
     *
     * @return overflow.
     */
    public int getOverflow() {
        return overflow;
    }

    @Override
    public String toString() {
        if (faceUp) {
            return rank.getName() + " " + suit.getName() + " (" + getPrice() + ")";
        }
        return closeCard;
    }

    /**
     * get price is card. 2 == 2, 3 == 3, ..., 10 == 10, J, Q, K == 10, A == (1 or 11).
     *
     * @return price if {@code faceUp} else 0.
     */
    public int getPrice() {
        if (!faceUp) {
            return 0;
        }
        return rank.getPoints() + overflow;
    }
}