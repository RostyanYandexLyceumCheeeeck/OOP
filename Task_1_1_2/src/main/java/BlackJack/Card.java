package BlackJack;

import static BlackJack.Constants.*;


/**
 * Class card.
 */
public class Card {
    private int overflow;
    private final int value;
    public boolean faceUp;

    /**
     * create instance of the class.
     *
     * @param value in the range [0, {@code numberOfCards - 1}].
     * @param isFaceUp shows if the card is turned over.
     */
    public Card(int value, boolean isFaceUp)  throws Exception {
        if (value < 0) throw new Exception("Card value cannot be negative");
        this.overflow = 0;
        this.value = value % numberOfCards;
        this.faceUp = isFaceUp;
    }

     /**
      * setup overflow. if card is Ace, ==> this.overflow = -10.
      */
    public void setOverflow() {
        this.overflow = ((value % numberOfRanks) == indexAce ? -overflowAce : 0);
    }

    /**
     * reset overflow.
     */
    public void resetOverflow() {
        this.overflow = 0;
    }

    /**
     * get overflow.
     * @return overflow.
     */
    public int getOverflow() {
        return overflow;
    }

    @Override
    public String toString() {
        if (faceUp) {
            return ranks[value % numberOfRanks] + " " + suits[value / numberOfRanks] + " (" + getPrice() + ")";
        }
        return closeCard;
    }

    /**
     * get price is card. 2 == 2, 3 == 3, ..., 10 == 10, J, Q, K == 10, A == (1 or 11).
     * @return price if {@code faceUp} else 0.
     */
    public int getPrice() {
        if (!faceUp) {
            return 0;
        }
        int t = value % numberOfRanks;
        return Math.min(t + 2, 10) + (t == indexAce ? 1 : 0) + overflow;
    }
}
