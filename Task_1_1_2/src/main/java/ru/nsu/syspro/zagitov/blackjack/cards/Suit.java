package ru.nsu.syspro.zagitov.blackjack.cards;

public enum Suit {
    CLUBS(0, "Треф"),
    DIAMONDS(1, "Бубен"),
    HEARTS(2, "Черви"),
    SPADES(3, "Пики");

    final int points;
    final String name;

    Suit(int points, String name) {
        this.points = points;
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public static Suit findByName(String name) throws IllegalArgumentException {
        for (Suit suit : Suit.values()) {
            if (suit.getName().equals(name)) {
                return suit;
            }
        }
        throw new IllegalArgumentException();
    }

}
