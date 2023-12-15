package vendingmachine.domain;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class Money implements Comparable<Money> {

    private static final int UNIT = 10;
    private int money;

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

    private static void validateSize(final int money) {
        if (money < UNIT) {
            throw new VendingMachineException(ErrorMessage.NOT_ENOUGH_MONEY);
        }
    }

    private static void validateUnit(final int money) {
        if (money % UNIT != 0) {
            throw new VendingMachineException(ErrorMessage.NOT_UNIT);
        }
    }

    public void subMoney(final Money other) {
        this.money -= other.money;
    }

    public boolean isUnder(final Money other) {
        return this.money < other.money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public int compareTo(Money o) {
        return this.money - o.money;
    }
}
