package ru.nsu.syspro.zagitov.blackjack;

import static ru.nsu.syspro.zagitov.blackjack.Constants.closeCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * testing class Card.
 */
public class TestPlayer {

    @Test
    public void testAddCard0() throws Exception {
        Player player = new Player();

        Card jackOfHearts = new Card(13 * 2 + 9, true);
        Assertions.assertFalse(player.addCard(jackOfHearts));
        Assertions.assertEquals(10, player.getScore());
    }

    @Test
    public void testAddCard1() throws Exception {
        Player player = new Player();

        Card jackOfHearts = new Card(13 * 2 + 9, true);
        Card aceOfHearts = new Card(13 * 2 + 12, true);

        Assertions.assertFalse(player.addCard(jackOfHearts));
        Assertions.assertFalse(player.addCard(aceOfHearts));

        Assertions.assertEquals(21, player.getScore());
    }

    @Test
    public void testAddCard2() throws Exception {
        Player player = new Player();

        Card jackOfHearts = new Card(13 * 2 + 9, true);
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        Card aceOfSpades = new Card(13 * 3 + 12, true);

        Assertions.assertFalse(player.addCard(jackOfHearts));
        Assertions.assertFalse(player.addCard(aceOfHearts));
        Assertions.assertFalse(player.addCard(aceOfSpades));

        Assertions.assertEquals(12, player.getScore());
    }

    @Test
    public void testAddCard3() throws Exception {
        Player player = new Player();

        Card jackOfHearts = new Card(13 * 2 + 9, true);
        Card jackOfSpades = new Card(13 * 3 + 9, true);
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        Card aceOfSpades = new Card(13 * 3 + 12, true);

        Assertions.assertFalse(player.addCard(jackOfHearts));
        Assertions.assertFalse(player.addCard(jackOfSpades));
        Assertions.assertFalse(player.addCard(aceOfHearts));
        Assertions.assertTrue(player.addCard(aceOfSpades));

        Assertions.assertEquals(22, player.getScore());
    }

    @Test
    public void testToString0() throws Exception {
        Player player = new Player();

        Card jackOfHearts = new Card(13 * 2 + 9, false);
        player.addCard(jackOfHearts);

        String expectedMessage = "[" + closeCard + "]";
        Assertions.assertEquals(expectedMessage, player.toString());
    }

    @Test
    public void testToString1() throws Exception {
        Player player = new Player();

        Card jackOfHearts = new Card(13 * 2 + 9, true);
        player.addCard(jackOfHearts);

        String expectedMessage = "[Валет Черви (10)] ==> 10";
        Assertions.assertEquals(expectedMessage, player.toString());
    }

    @Test
    public void testToString2() throws Exception {
        Player player = new Player();

        Card jackOfHearts = new Card(13 * 2 + 9, true);
        Card aceOfHearts = new Card(13 * 2 + 12, false);

        player.addCard(jackOfHearts);
        player.addCard(aceOfHearts);

        String expectedMessage = "[Валет Черви (10), " + closeCard + "]";
        Assertions.assertEquals(expectedMessage, player.toString());
    }

    @Test
    public void testToString3() throws Exception {
        Player player = new Player();

        Card jackOfHearts = new Card(13 * 2 + 9, true);
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        Card aceOfSpades = new Card(13 * 3 + 12, true);

        player.addCard(jackOfHearts);
        player.addCard(aceOfHearts);
        player.addCard(aceOfSpades);

        String expectedMessage = "[Валет Черви (10), Туз Черви (1), Туз Пики (1)] ==> 12";
        Assertions.assertEquals(expectedMessage, player.toString());
    }

    @Test
    public void testToString4() throws Exception {
        Player player = new Player();

        Card jackOfHearts = new Card(13 * 2 + 9, true);
        Card jackOfSpades = new Card(13 * 3 + 9, true);
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        Card aceOfSpades = new Card(13 * 3 + 12, true);

        player.addCard(jackOfHearts);
        player.addCard(jackOfSpades);
        player.addCard(aceOfHearts);
        player.addCard(aceOfSpades);

        String expectedMessage =
                "[Валет Черви (10), Валет Пики (10), Туз Черви (1), Туз Пики (1)] ==> 22";
        Assertions.assertEquals(expectedMessage, player.toString());
    }
}