package vendingmachine.exception;

public enum ErrorMessage {

    NOT_NUMBER_MONEY("금액은 숫자여야 합니다."),
    NOT_UNIT("금액은 10원 단위로 입력해야합니다."),
    NOT_ENOUGH_MONEY("금액은 10원 이상이어야합니다."),
    INVALID_COIN_NUMBER("코인 숫자가 잘못되었습니다."),
    INVALID_ITEM_FORMAT("아이템 입력 형식이 잘못되었습니다."),
    NEGATIVE_COUNT("수량이 음수일 수 없습니다."),
    NOT_NUMBER_COUNT("수량은 숫자여야 합니다."),
    UNDER_ITEM_MIN_MONEY("가격 100원 미만은 생성할 수 없습니다."),
    NOT_ENOUGH_ITEM("구입할 수 있는 아이템이 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
