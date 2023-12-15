package vendingmachine.exception;

public enum ErrorMessage {

    NOT_NUMBER_MONEY("금액은 숫자여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
