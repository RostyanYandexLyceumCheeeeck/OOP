package ru.nsu.syspro.zagitov.blackjack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.zagitov.blackjack.cards.Deck;

import java.util.Scanner;

import static ru.nsu.syspro.zagitov.blackjack.Constants.blackJack;


/**
 * testing class BlackJack.
 */
public class TestBlackJack {
    @Test
    public void testSetDeck() throws Exception {
        BlackJack game = new BlackJack();
        int countDecks = -2;
        while (countDecks < 1) {
            Assertions.assertFalse(game.setDeck(countDecks));
            countDecks++;
        }
        Assertions.assertTrue(game.setDeck(countDecks));
    }

    @Test
    public void testSetCountRounds() {
        BlackJack game = new BlackJack();
        int countRounds = -2;
        while (countRounds < 1) {
            Assertions.assertFalse(game.setCountRounds(countRounds));
            countRounds++;
        }
        Assertions.assertTrue(game.setCountRounds(countRounds));
    }

    @Test
    public void testRound0() throws Exception {
        BlackJack game = new BlackJack();
        Scanner console = new Scanner("0\n");
        game.deck = new Deck(1);
        Player player = new Player();
        Player dealer = new Player();

        BlackJack.WhoWin result = game.round(console, 0, player, dealer);
        if (player.getScore() > blackJack) {
            Assertions.assertEquals(BlackJack.WhoWin.DEALER, result);
        } else if (dealer.getScore() > blackJack) {
            Assertions.assertEquals(BlackJack.WhoWin.PLAYER, result);
        } else if (player.getScore() > dealer.getScore()) {
            Assertions.assertEquals(BlackJack.WhoWin.PLAYER, result);
        } else if (player.getScore() < dealer.getScore()) {
            Assertions.assertEquals(BlackJack.WhoWin.DEALER, result);
        } else if (player.getScore() == dealer.getScore()) {
            Assertions.assertEquals(BlackJack.WhoWin.DRAW, result);
        }
    }

    @Test
    public void testRound1() throws Exception {
        BlackJack game = new BlackJack();
        Scanner console = new Scanner("1\n0\n");
        game.deck = new Deck(1);
        Player player = new Player();
        Player dealer = new Player();

        BlackJack.WhoWin result = game.round(console, 0, player, dealer);
        if (player.getScore() > blackJack) {
            Assertions.assertEquals(BlackJack.WhoWin.DEALER, result);
        } else if (dealer.getScore() > blackJack) {
            Assertions.assertEquals(BlackJack.WhoWin.PLAYER, result);
        } else if (player.getScore() > dealer.getScore()) {
            Assertions.assertEquals(BlackJack.WhoWin.PLAYER, result);
        } else if (player.getScore() < dealer.getScore()) {
            Assertions.assertEquals(BlackJack.WhoWin.DEALER, result);
        } else if (player.getScore() == dealer.getScore()) {
            Assertions.assertEquals(BlackJack.WhoWin.DRAW, result);
        }
    }

    @Test
    public void testRound2() throws Exception {
        BlackJack game = new BlackJack();
        Scanner console = new Scanner("1\n1\n0\n");
        game.deck = new Deck(1);
        Player player = new Player();
        Player dealer = new Player();

        BlackJack.WhoWin result = game.round(console, 0, player, dealer);
        if (player.getScore() > blackJack) {
            Assertions.assertEquals(BlackJack.WhoWin.DEALER, result);
        } else if (dealer.getScore() > blackJack) {
            Assertions.assertEquals(BlackJack.WhoWin.PLAYER, result);
        } else if (player.getScore() > dealer.getScore()) {
            Assertions.assertEquals(BlackJack.WhoWin.PLAYER, result);
        } else if (player.getScore() < dealer.getScore()) {
            Assertions.assertEquals(BlackJack.WhoWin.DEALER, result);
        } else if (player.getScore() == dealer.getScore()) {
            Assertions.assertEquals(BlackJack.WhoWin.DRAW, result);
        }
    }
}