package vendingmachine.domain;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class Money {

    private final int money;

    public Money(final String source) {
        final int money = convertToInt(source);
        this.money = money;
    }

    public static int convertToInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw new VendingMachineException(ErrorMessage.NOT_NUMBER_MONEY);
        }
    }
}
