package ru.nsu.syspro.zagitov.blackjack;

import static ru.nsu.syspro.zagitov.blackjack.Constants.closeCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * testing class Card.
 */
public class TestCard {

    @Test
    public void testException() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            Card card = new Card(-1, false);
            card.getPrice();
        });

        String expectedMessage = "Card value cannot be negative";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testSetOverflow0() throws Exception {
        Card jackOfHearts = new Card(13 * 2 + 9, true);
        jackOfHearts.setOverflow();
        Assertions.assertEquals(0, jackOfHearts.getOverflow());
    }

    @Test
    public void testSetOverflow1() throws Exception {
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        aceOfHearts.setOverflow();
        Assertions.assertEquals(-10, aceOfHearts.getOverflow());
    }

    @Test
    public void testResetOverflow() throws Exception {
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        aceOfHearts.setOverflow();
        aceOfHearts.resetOverflow();
        Assertions.assertEquals(0, aceOfHearts.getOverflow());
    }

    @Test
    public void testGetPrice0() throws Exception {
        Card jackOfHearts = new Card(13 * 2 + 9, false);
        Assertions.assertEquals(0, jackOfHearts.getPrice());
    }

    @Test
    public void testGetPrice1() throws Exception {
        Card jackOfHearts = new Card(13 * 2 + 9, true);
        Assertions.assertEquals(10, jackOfHearts.getPrice());
    }

    @Test
    public void testGetPrice2() throws Exception {
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        Assertions.assertEquals(11, aceOfHearts.getPrice());
    }

    @Test
    public void testGetPrice3() throws Exception {
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        aceOfHearts.setOverflow();
        Assertions.assertEquals(1, aceOfHearts.getPrice());
    }

    @Test
    public void testToString0() throws Exception {
        Card jackOfHearts = new Card(13 * 2 + 9, false);
        Assertions.assertEquals(closeCard, jackOfHearts.toString());
    }

    @Test
    public void testToString1() throws Exception {
        Card jackOfHearts = new Card(13 * 2 + 9, true);
        Assertions.assertEquals("Валет Черви (10)", jackOfHearts.toString());
    }

    @Test
    public void testToString2() throws Exception {
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        Assertions.assertEquals("Туз Черви (11)", aceOfHearts.toString());
    }

    @Test
    public void testToString3() throws Exception {
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        aceOfHearts.setOverflow();
        Assertions.assertEquals("Туз Черви (1)", aceOfHearts.toString());
    }

    @Test
    public void testToString4() throws Exception {
        Card aceOfHearts = new Card(13 * 2 + 12, true);
        aceOfHearts.setOverflow();
        aceOfHearts.resetOverflow();
        Assertions.assertEquals("Туз Черви (11)", aceOfHearts.toString());
    }
}
