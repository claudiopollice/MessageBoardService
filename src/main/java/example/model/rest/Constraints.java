package example.model.rest;

public class Constraints {

    public static final int TITLE_MIN_LENGTH = 1;
    public static final int TITLE_MAX_LENGTH = 40;
    public static final String TITLE_LENGTH_VIOLATON_MESSAGE = "Title must contain between 1 and 40 characters.";

    public static final int CONTENT_MIN_LENGTH = 1;
    public static final int CONTENT_MAX_LENGTH = 140;
    public static final String CONTENT_LENGTH_VIOLATON_MESSAGE = "Content must contain between 1 and 140 characters.";

    public static final int SENDER_MIN_LENGTH = 1;
    public static final int SENDER_MAX_LENGTH = 30;
    public static final String SENDER_LENGTH_VIOLATON_MESSAGE = "Sender must contain between 1 and 30 characters.";

    public static final int URL_MIN_LENGTH = 7;
    public static final int URL_MAX_LENGTH = 60;
    public static final String URL_LENGTH_VIOLATON_MESSAGE = "Url must contain between 7 and 60 characters and be a valid Url.";

}
