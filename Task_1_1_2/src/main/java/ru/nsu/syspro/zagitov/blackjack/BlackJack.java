package ru.nsu.syspro.zagitov.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static ru.nsu.syspro.zagitov.blackjack.Constants.blackJack;
import static ru.nsu.syspro.zagitov.blackjack.Constants.continueUpCard;
import static ru.nsu.syspro.zagitov.blackjack.Constants.dealerLimit;
import static ru.nsu.syspro.zagitov.blackjack.Constants.exitGame;
import static ru.nsu.syspro.zagitov.blackjack.Constants.indexCloseCardDealer;
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
import static ru.nsu.syspro.zagitov.blackjack.Constants.numberOfCards;
import static ru.nsu.syspro.zagitov.blackjack.Constants.stopUpCard;



/**
 * Class Game "Консольный блэкджек".
 */
public class BlackJack {
    private int countDecks;
    private int countRounds;
    private ArrayList<Card> cards;

    interface MyInterface {
        boolean openCard(Player human, String prefix, int index);
    }

    /**
     * create instance of the class.
     */
    public BlackJack() {
        this.countDecks = 1;
    }

    /**
     * setup {@code countDecks}.
     *
     * @param newCountDecks is input count decks.
     * @return false if {@code newCountDecks < 1} else true.
     */
    private boolean setCountDecks(int newCountDecks) {
        if (newCountDecks < 1) {
            return false;
        }
        this.countDecks = newCountDecks;
        return true;
    }

    /**
     * setup {@code countDecks}.
     *
     * @param newCountRounds is input count rounds.
     * @return false if {@code newCountRounds < 1} else true.
     */
    private boolean setCountRounds(int newCountRounds) {
        if (newCountRounds < 1) {
            return false;
        }
        this.countRounds = newCountRounds;
        return true;
    }

    /**
     * initialize {@code cards} and shuffle.
     */
    private void setCards() throws Exception {
        this.cards = new ArrayList<>(this.countDecks * numberOfCards);
        for (int i = 0; i < this.countDecks * numberOfCards; i++) {
            this.cards.add(new Card(i, true));
        }
        shuffleCards();
    }

    /**
     * reset overflow is cards, and shuffle {@code cards}.
     */
    private void shuffleCards() {
        for (Card card : this.cards) {
            card.resetOverflow();
        }
        Collections.shuffle(this.cards);
    }

    /**
     * start game.
     */
    public void startGame() throws Exception {
        System.out.println("Добро пожаловать в Блэкджек!");
        Scanner console = new Scanner(System.in);

        System.out.println("Сколько колод используем? " + messageMaxDecks);
        while (!setCountDecks(console.nextInt())) {
            System.out.println(messageMaxDecks);
        }

        System.out.println("Сколько раундов играем? " + messageMaxRounds);
        while (!setCountRounds(console.nextInt())) {
            System.out.println(messageMaxRounds);
        }
        setCards();

        try {
            System.out.println(messageExitGame0);

            int scorePlayer = 0;
            int scoreDealer = 0;
            for (int roundNumber = 1; roundNumber <= this.countRounds; roundNumber++) {
                int resultRound = round(console, roundNumber);
                scorePlayer += (resultRound > 0 ? 1 : 0);
                scoreDealer += (resultRound < 0 ? 1 : 0);

                String whoWinRound = (resultRound > 0
                        ? messageWinRoundPlayer0 : messageWinRoundDealer0);
                if (resultRound == 0) {
                    whoWinRound = messageDrawRound;
                }

                String currentLeader = (scorePlayer > scoreDealer
                        ? messageWinRoundPlayer1 : messageWinRoundDealer1);
                if (scorePlayer == scoreDealer) {
                    currentLeader = "";
                }

                System.out.println(whoWinRound + " Счёт " + scorePlayer + ":" + scoreDealer
                        + currentLeader + "\n");
                this.cards.get(indexCloseCardDealer).faceUp = true;
                shuffleCards();
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

    /**
     * function start round.
     *
     * @param console where the user enters the data.
     * @param number round number.
     * @return -1 if win Dealer else (1 if You win else 0).
     * @throws Exception if you have entered {@code exitGame} or
     * not {@code messagePlayer1}.
     */
    private int round(Scanner console, int number) throws Exception {
        int draw = 0;
        int winPlayer = 1;
        int winDealer = -1;
        Player player = new Player();
        Player dealer = new Player();
        boolean roundLoose;
        int index;

        MyInterface copyPaste = (human, prefix, index1) -> {
            Card newCard = cards.get(index1);
            System.out.println(prefix + newCard);

            boolean result = human.addCard(newCard);
            printHands(player, dealer);
            System.out.println();

            return result;
        };

        for (index = 0; index <= indexCloseCardDealer; index += 2) {
            player.addCard(this.cards.get(index));
            dealer.addCard(this.cards.get(index + 1));
        }
        this.cards.get(indexCloseCardDealer).faceUp = false;

        System.out.println("Раунд " + number + "\nДилер раздал карты");
        printHands(player, dealer);

        if (player.getScore() == blackJack) {
            return winPlayer;
        }

        System.out.println(messagePlayer0);
        int flag = console.nextInt();

        if (flag != exitGame && flag != stopUpCard && flag != continueUpCard) {
            throw new Exception(messagePlayer1);
        }

        while (flag == continueUpCard) {
            roundLoose = copyPaste.openCard(player, messagePlayer2, ++index);

            if (roundLoose) {
                return winDealer;
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

        this.cards.get(indexCloseCardDealer).faceUp = true;
        System.out.println(messageDealer0);
        System.out.println(messageDealer1 + this.cards.get(indexCloseCardDealer));

        printHands(player, dealer);
        System.out.println();

        if (dealer.getScore() == blackJack) {
            return winDealer;
        }

        while (dealer.getScore() < dealerLimit) {
            roundLoose = copyPaste.openCard(dealer, messageDealer2, ++index);

            if (roundLoose) {
                return winPlayer;
            }
        }

        if (player.getScore() == dealer.getScore()) {
            return draw;
        }
        return (player.getScore() > dealer.getScore() ? winPlayer : winDealer);
    }
}
