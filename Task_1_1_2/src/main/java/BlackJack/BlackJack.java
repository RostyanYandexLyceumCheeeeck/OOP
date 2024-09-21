package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static BlackJack.Constants.*;

/**
 * Class Game "Консольный блэкджек".
 */
public class BlackJack {
    private int countDecks;
    private int countRounds;
    private ArrayList<Card> cards;

    interface myInterface {
        boolean openCard(Player human, String prefix, int index);
    }

    /**
     * create instance of the class.
     */
    public BlackJack(){
        this.countDecks = 1;
    }

    /**
     * setup {@code countDecks}.
     * @param newCountDecks is input count decks.
     * @return false if {@code newCountDecks < 1} else true 
     */
    private boolean setCountDecks(int newCountDecks){
        if (newCountDecks < 1) return false;
        this.countDecks = newCountDecks;
        return true;
    }

    /**
     * setup {@code countDecks}.
     * @param newCountRounds is input count rounds.
     * @return false if {@code newCountRounds < 1} else true
     */
    private boolean setCountRounds(int newCountRounds){
        if (newCountRounds < 1) return false;
        this.countRounds = newCountRounds;
        return true;
    }

    /**
     * initialize {@code cards} and shuffle.
     */
    private void setCards() throws Exception {
        this.cards = new ArrayList<Card>(this.countDecks * numberOfCards);
        for (int i = 0; i < this.countDecks * numberOfCards; i++) {
            this.cards.add(new Card(i, true));
        }
        shuffleCards();
    }

    /**
     * reset overflow is cards, and shuffle {@code cards}.
     */
    private void shuffleCards(){
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

        System.out.println("Сколько колод используем?");
        while (!setCountDecks(console.nextInt())) {
            System.out.println("Введите целое число больше 0 !!!");
        }

        System.out.println("Сколько раундов играем?");
        while (!setCountRounds(console.nextInt())) {
            System.out.println("Введите целое число больше 0 !!!");
        }
        setCards();

        try {
            System.out.println("--------------------------------------------------");
            System.out.println("!!! Вы можете выйти из игры, если введёте “" + exitGame + "” !!!");
            System.out.println("--------------------------------------------------");

            int scorePlayer = 0;
            int scoreDealer = 0;
            for (int roundNumber = 1; roundNumber <= this.countRounds; roundNumber++) {
                int resultRound = startRound(console, roundNumber);
                scorePlayer += (resultRound > 0 ? 1 : 0);
                scoreDealer += (resultRound < 0 ? 1 : 0);

                String str1 = (resultRound > 0 ? "Вы выиграли раунд!" : "Вы проиграли раунд!");
                if (resultRound == 0) str1 = "Этот раунд вы сыграли вничью!";

                String str2 = (scorePlayer > scoreDealer ? " в вашу пользу." : " в пользу Дилера.");
                if (scorePlayer == scoreDealer) str2 = "";

                System.out.println(str1 + " Счёт " + scorePlayer + ":" + scoreDealer + str2 + "\n");
                this.cards.get(indexCloseCardDealer).faceUp = true;
                shuffleCards();
            }
        } catch (Exception MyException) {
            System.out.println(MyException.getMessage());
        }
        console.close();
    }

    /**
     * displays your and dealer's cards.
     * @param player You (instance of the class PLayer).
     * @param dealer Dealer (instance of the class PLayer).
     */
    private void printHands(Player player, Player dealer){
        System.out.println("Ваши карты:   " + player);
        System.out.println("Карты дилера: " + dealer);
    }

    /**
     * function start round.
     * @param console where the user enters the data.
     * @param number round number.
     * @return -1 if win Dealer else (1 if You win else 0).
     * @throws Exception if you have entered {@code exitGame}.
     */
    private int startRound(Scanner console, int number) throws Exception {
        int draw = 0;
        int winPlayer = 1;
        int winDealer = -1;
        Player player = new Player();
        Player dealer = new Player();
        boolean roundLoose;
        int index;

        myInterface copyPaste = (human, prefix, index1) -> {
            Card newCard = cards.get(index1);
            boolean result = human.addCard(newCard);

            System.out.println(prefix + newCard);
            printHands(player, dealer);
            System.out.println();

            return result;
        };

        for (index = 0; index <= indexCloseCardDealer; index++) {
            player.addCard(this.cards.get(index));
            dealer.addCard(this.cards.get(++index));
        }
        this.cards.get(indexCloseCardDealer).faceUp = false;

        System.out.println("Раунд " + number + "\nДилер раздал карты");
        printHands(player, dealer);

        if (player.getScore() == blackJack) return winPlayer;

        System.out.println("Ваш ход\n-------\nВведите “" + continueUpCard +
                "”, чтобы взять карту, и “" + stopUpCard + "”, чтобы остановиться...");
        int flag = console.nextInt();

        while (flag == continueUpCard) {
            roundLoose = copyPaste.openCard(player, "Вы открыли карту ", ++index);

            if (roundLoose) return winDealer;

            System.out.println("Ваш ход\n-------\nВведите “" + continueUpCard +
                    "”, чтобы взять карту, и “" + stopUpCard + "”, чтобы остановиться...");
            flag = console.nextInt();
        }

        if (flag == exitGame) throw new Exception("Вы досрочно закочили игру!");

        this.cards.get(indexCloseCardDealer).faceUp = true;
        System.out.println("\nХод дилера\n-------");
        System.out.println("Дилер открывает закрытую карту " + this.cards.get(indexCloseCardDealer));

        printHands(player, dealer);
        System.out.println();

        if (dealer.getScore() == blackJack) return winDealer;

        while (dealer.getScore() < dealerLimit) {
            roundLoose = copyPaste.openCard(dealer, "Дилер открывает карту ", ++index);

            if (roundLoose) return winPlayer;
        }

        if (player.getScore() == dealer.getScore()) return draw;
        return (player.getScore() > dealer.getScore() ? winPlayer : winDealer);
    }
}
