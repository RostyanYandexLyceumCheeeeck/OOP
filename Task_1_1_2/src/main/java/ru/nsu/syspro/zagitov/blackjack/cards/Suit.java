package ru.nsu.syspro.zagitov.blackjack.cards;

/**
 * Suit card.
 */
public enum Suit {
    CLUBS("Треф"),
    DIAMONDS("Бубен"),
    HEARTS("Черви"),
    SPADES("Пики");

    final String name;

    /**
     * default constructor.
     *
     * @param name name suit card.
     */
    Suit(String name) {
        this.name = name;
    }

    /**
     * Getter name suit card.
     *
     * @return name suit.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter suit by name card.
     *
     * @param name name suit card.
     * @return {@code Suit} suit card.
     * @throws IllegalArgumentException if name suit not found.
     */
    public static Suit findByName(String name) throws IllegalArgumentException {
        for (Suit suit : Suit.values()) {
            if (suit.getName().equals(name)) {
                return suit;
            }
        }
        throw new IllegalArgumentException();
    }
}
