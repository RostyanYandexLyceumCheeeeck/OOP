package ru.nsu.syspro.zagitov.blackjack;


/**
 * Class for entry point to the program.
 */
public class Main {
    /**
     * start pointer program.
     *
     * @param args arguments of command line.
     */
    public static void main(String[] args) throws Exception {
        BlackJack game = new BlackJack();
        game.startGame();
    }
}