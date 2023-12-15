package vendingmachine.domain;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class Money {

    private static final int UNIT = 10;
    private final int money;

    public Money(final String source) {
        final int money = convertToInt(source);
        validateSize(money);
        validateUnit(money);
        this.money = money;
    }

    public static int convertToInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw new VendingMachineException(ErrorMessage.NOT_NUMBER_MONEY);
        }
    }

    private static void validateSize(int money) {
        if (money < UNIT) {
            throw new VendingMachineException(ErrorMessage.NOT_ENOUGH_MONEY);
        }
    }

    private static void validateUnit(int money) {
        if (money % UNIT != 0) {
            throw new VendingMachineException(ErrorMessage.NOT_UNIT);
        }
    }
}
