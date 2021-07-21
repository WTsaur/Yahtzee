package Yahtzee;

public class Constants {
    public static final String APP_NAME = "Yahtzee";
    public static final int MAX_PLAYERS = 10;
    public static final int MIN_PLAYERS = 2;
    public static final String MENU_ERROR_MESSAGE = "Please enter at least " + MIN_PLAYERS + " players (maximum " + MAX_PLAYERS + ")";
    public static final String PLAYER_NAME_ERROR_MESSAGE = "That name has already been taken!";
    public static final String BASE_IMAGE_PATH = "/Yahtzee/images/";
    public static final String BACKGROUND_IMAGE_PATH = BASE_IMAGE_PATH + "background-green.jpg";
    public static final String DEFAULT_DICE = BASE_IMAGE_PATH + "Dice-1.png";
    public static final String ROLLBTN_IMAGE_PATH = BASE_IMAGE_PATH + "Die.png";
    public static final String SCORECARD_IMAGE_PATH = BASE_IMAGE_PATH + "Scorecard.png";
    public static final String SCORE_NULL = "-";
}
