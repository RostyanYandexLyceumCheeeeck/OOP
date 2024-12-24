package ru.nsu.syspro.zagitov.blackjack;

import static ru.nsu.syspro.zagitov.blackjack.Constants.blackJack;
import static ru.nsu.syspro.zagitov.blackjack.Constants.continueUpCard;
import static ru.nsu.syspro.zagitov.blackjack.Constants.dealerLimit;
import static ru.nsu.syspro.zagitov.blackjack.Constants.exitGame;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageDealer0;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageDealer1;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageDealer2;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageDealer3;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageDrawRound;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageExitGame0;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageExitGame1;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageMaxDecks;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageMaxRounds;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messagePlayer0;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messagePlayer1;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messagePlayer2;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messagePlayer3;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageWinRoundDealer0;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageWinRoundDealer1;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageWinRoundPlayer0;
import static ru.nsu.syspro.zagitov.blackjack.Constants.messageWinRoundPlayer1;
import static ru.nsu.syspro.zagitov.blackjack.Constants.stopUpCard;

import java.util.Scanner;
import ru.nsu.syspro.zagitov.blackjack.cards.Card;
import ru.nsu.syspro.zagitov.blackjack.cards.Deck;

/**
 * Class Game "Консольный блэкджек".
 */
public class BlackJack {
    protected Deck deck = null;
    private int countRounds = 0;

    interface MyInterface {
        boolean openCard(Player human, String prefix, int index) throws Exception;
    }

    /**
     * Result round.
     */
    public enum WhoWin {
        DEALER,
        DRAW,
        PLAYER
    }

    /**
     * create instance of the class.
     */
    public BlackJack() {
        this.countRounds = 0;
    }

    /**
     * setup {@code countDecks}.
     *
     * @param newCountDecks is input count decks.
     * @return false if {@code newCountDecks < 1} else true.
     */
    protected boolean setDeck(int newCountDecks) throws Exception {
        if (newCountDecks < 1) {
            return false;
        }
        this.deck = new Deck(newCountDecks);
        return true;
    }

    /**
     * setup {@code countDecks}.
     *
     * @param newCountRounds is input count rounds.
     * @return false if {@code newCountRounds < 1} else true.
     */
    protected boolean setCountRounds(int newCountRounds) {
        if (newCountRounds < 1) {
            return false;
        }
        this.countRounds = newCountRounds;
        return true;
    }

    /**
     * start game.
     */
    public void startGame() throws Exception {
        System.out.println("Добро пожаловать в Блэкджек!");
        Scanner console = new Scanner(System.in);

        System.out.println("Сколько колод используем? " + messageMaxDecks);
        while (!setDeck(console.nextInt())) {
            System.out.println(messageMaxDecks);
        }

        System.out.println("Сколько раундов играем? " + messageMaxRounds);
        while (!setCountRounds(console.nextInt())) {
            System.out.println(messageMaxRounds);
        }

        try {
            System.out.println(messageExitGame0);

            int scorePlayer = 0;
            int scoreDealer = 0;
            for (int roundNumber = 0; roundNumber < this.countRounds; roundNumber++) {
                WhoWin resultRound = round(console, roundNumber + 1);
                scorePlayer += (resultRound == WhoWin.PLAYER ? 1 : 0);
                scoreDealer += (resultRound == WhoWin.DEALER ? 1 : 0);

                String whoWinRound = (resultRound == WhoWin.PLAYER
                        ? messageWinRoundPlayer0 : messageWinRoundDealer0);
                if (resultRound == WhoWin.DRAW) {
                    whoWinRound = messageDrawRound;
                }

                String currentLeader = (scorePlayer > scoreDealer
                        ? messageWinRoundPlayer1 : messageWinRoundDealer1);
                if (scorePlayer == scoreDealer) {
                    currentLeader = "";
                }

                System.out.println(whoWinRound + " Счёт " + scorePlayer + ":" + scoreDealer
                        + currentLeader + "\n");
            }
        } catch (Exception myException) {
            System.out.println(myException.getMessage());
        }
        console.close();
    }

    /**
     * displays your and dealer's cards.
     *
     * @param player You (instance of the class PLayer).
     * @param dealer Dealer (instance of the class PLayer).
     */
    private void printHands(Player player, Player dealer) {
        System.out.println(messagePlayer3 + player);
        System.out.println(messageDealer3 + dealer);
    }

    private WhoWin round(Scanner console, int number) throws Exception {
        Player player = new Player();
        Player dealer = new Player();
        return round(console, number, player, dealer);
    }

    /**
     * function start round.
     *
     * @param console where the user enters the data.
     * @param number round number.
     * @return enum WhoWin.
     * @throws Exception if you have entered {@code exitGame} or not {@code messagePlayer1}.
     */
    protected WhoWin round(Scanner console, int number, Player player, Player dealer)
            throws Exception {
        boolean roundLoose;
        int index;

        MyInterface copyPaste = (human, prefix, index1) -> {
            Card newCard = this.deck.getCard();
            System.out.println(prefix + newCard);

            boolean result = human.addCard(newCard);
            printHands(player, dealer);
            System.out.println();

            return result;
        };

        for (index = 0; index < 2; index++) {
            player.addCard(this.deck.getCard());
            dealer.addCard(this.deck.getCard());
        }
        dealer.getLastCard().faceUp = false;

        System.out.println("Раунд " + number + "\nДилер раздал карты");
        printHands(player, dealer);

        if (player.getScore() == blackJack) {
            return WhoWin.PLAYER;
        }

        System.out.println(messagePlayer0);
        int flag = console.nextInt();

        if (flag != exitGame && flag != stopUpCard && flag != continueUpCard) {
            throw new Exception(messagePlayer1);
        }

        while (flag == continueUpCard) {
            roundLoose = copyPaste.openCard(player, messagePlayer2, ++index);

            if (roundLoose) {
                return WhoWin.DEALER;
            }

            System.out.println(messagePlayer0);
            flag = console.nextInt();

            if (flag != exitGame && flag != stopUpCard && flag != continueUpCard) {
                throw new Exception(messagePlayer1);
            }
        }

        if (flag == exitGame) {
            throw new Exception(messageExitGame1);
        }

        dealer.getLastCard().faceUp = true;
        System.out.println(messageDealer0);
        System.out.println(messageDealer1 + dealer.getLastCard());

        printHands(player, dealer);
        System.out.println();

        if (dealer.getScore() == blackJack) {
            return WhoWin.DEALER;
        }

        while (dealer.getScore() < dealerLimit) {
            roundLoose = copyPaste.openCard(dealer, messageDealer2, ++index);

            if (roundLoose) {
                return WhoWin.PLAYER;
            }
        }

        if (player.getScore() == dealer.getScore()) {
            return WhoWin.DRAW;
        }
        return (player.getScore() > dealer.getScore() ? WhoWin.PLAYER : WhoWin.DEALER);
    }
}