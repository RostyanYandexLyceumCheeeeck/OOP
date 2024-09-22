package blackJack;

/* class Constants my program. */
public class Constants {
    public static final String[] suits = {"Треф", "Бубен", "Черви", "Пики"};
    public static final String[] ranks = {"Двойка", "Тройка", "Четвёрка", "Пятёрка", "Шестёрка", "Семёрка",
            "Восьмёрка", "Девятка", "Десятка", "Валет", "Дама", "Король", "Туз"};

    public static final int numberOfCards = 52;
    public static final int numberOfRanks = 13;

    public static final int blackJack = 21;
    public static final int dealerLimit = 17;

    public static final int overflowAce = 10;
    public static final int indexCloseCardDealer = 3;
    public static final int indexAce = ranks.length - 1;

    public static final int exitGame = -1;
    public static final int stopUpCard = 0;
    public static final int continueUpCard = 1;

    public static final String closeCard = "<закрытая карта>";
    public static final String messageExitGame0 = "--------------------------------------------------\n" +
                                                  "!!! Вы можете выйти из игры, если введёте “" + exitGame + "” !!!" +
                                                  "--------------------------------------------------";
    public static final String messageExitGame1 = "Вы досрочно закочили игру!";

    public static final String messagePlayer0 = "Ваш ход\n-------\nВведите “" + continueUpCard +
                                                    "”, чтобы взять карту, и “" + stopUpCard +
                                                    "”, чтобы остановиться...";
    public static final String messagePlayer1 = "Введите число из возможных: [" + exitGame + ", " +
            stopUpCard + ", " + continueUpCard + "] !!!";
    public static final String messagePlayer2 = "Вы открыли карту ";
    public static final String messagePlayer3 = "Ваши карты:   ";


    public static final String messageDealer0 = "\nХод дилера\n-------";
    public static final String messageDealer1 = "Дилер открывает закрытую карту ";
    public static final String messageDealer2 = "Дилер открывает карту ";
    public static final String messageDealer3 = "Карты дилера: ";

    public static final String messageWinRoundPlayer0 = "Вы выиграли раунд!";
    public static final String messageWinRoundDealer0 = "Вы проиграли раунд!";
    public static final String messageDrawRound = "Этот раунд вы сыграли вничью!";

    public static final String messageWinRoundPlayer1 = " в вашу пользу.";
    public static final String messageWinRoundDealer1 = " в пользу Дилера.";
}
