package BlackJack;

/* class Constants my program. */
public class Constants {
    public static final String closeCard = "<закрытая карта>";
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
    public static final int continueUpCard = 2;


}
