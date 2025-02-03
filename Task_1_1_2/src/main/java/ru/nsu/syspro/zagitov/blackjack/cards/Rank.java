package ru.nsu.syspro.zagitov.blackjack.cards;

/**
 * Base price and name card.
 */
public enum Rank {
    TWO(2, "Двойка"),
    THREE(3, "Тройка"),
    FOUR(4, "Четвёрка"),
    FIVE(5, "Пятёрка"),
    SIX(6, "Шестёрка"),
    SEVEN(7, "Семёрка"),
    EIGHT(8, "Восьмёрка"),
    NINE(9, "Девятка"),
    TEN(10, "Десятка"),
    JACK(10, "Валет"),
    QUEEN(10, "Дама"),
    KING(10, "Король"),
    ACE(11, "Туз");

    final int points;
    final String name;

    /**
     * default constructor.
     *
     * @param points price card.
     * @param name name rank card.
     */
    Rank(int points, String name) {
        this.points = points;
        this.name = name;
    }

    /**
     * Getter price card.
     *
     * @return price.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Getter name rank card.
     *
     * @return name rank.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter rank by name card.
     *
     * @param name name rank card.
     * @return {@code Rank} rank card.
     * @throws IllegalArgumentException if name rank not found.
     */
    public static Rank findByName(String name) throws IllegalArgumentException {
        for (Rank rank : values()) {
            if (rank.getName().equals(name)) {
                return rank;
            }
        }
        throw new IllegalArgumentException();
    }
}