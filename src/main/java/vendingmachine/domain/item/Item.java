package vendingmachine.domain.item;

import vendingmachine.domain.Money;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class Item {

    private static final int MIN_COUNT = 0;
    private static final Money MIN_MONEY = new Money("100");
    private final String name;
    private final Money money;
    private final int count;

    public Item(final String name, final Money money, final int count) {
        validateCount(count);
        validateMoney(money);
        this.name = name;
        this.money = money;
        this.count = count;
    }

    private static void validateCount(final int count) {
        if (count < MIN_COUNT) {
            throw new VendingMachineException(ErrorMessage.NEGATIVE_COUNT);
        }
    }

    private static void validateMoney(final Money money) {
        if (money.isUnder(MIN_MONEY)) {
            throw new VendingMachineException(ErrorMessage.UNDER_ITEM_MIN_MONEY);
        }
    }
}
