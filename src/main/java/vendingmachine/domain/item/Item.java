package vendingmachine.domain.item;

import vendingmachine.domain.Money;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class Item {

    private final int MIN_COUNT = 0;
    private final String name;
    private final Money money;
    private final int count;

    public Item(String name, Money money, int count) {
        if (count < MIN_COUNT) {
            throw new VendingMachineException(ErrorMessage.NEGATIVE_COUNT);
        }

        this.name = name;
        this.money = money;
        this.count = count;
    }
}
