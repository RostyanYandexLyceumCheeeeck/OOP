package ru.nsu.syspro.zagitov.blackJack;


/**
 * Class for entry point to the program.
 */
public class Main {
    /**
     * @param args arguments of command line.
     */
    public static void main(String[] args) throws Exception {
        BlackJack game = new BlackJack();
        game.startGame();
    }
}