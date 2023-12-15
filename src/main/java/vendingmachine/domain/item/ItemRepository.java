package vendingmachine.domain.item;

import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.Money;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class ItemRepository {

    private final List<Item> items = new ArrayList<>();

    public void addItems(final List<Item> item) {
        items.addAll(item);
    }

    public Money getItemMinMoney() {
        return items.stream()
                .filter(Item::isNotEmpty)
                .map(Item::getMoney)
                .min(Money::compareTo)
                .orElseThrow(() -> new VendingMachineException(ErrorMessage.INVALID_ITEM));
    }

    public Item findByName(final String name) {
        return items.stream()
                .filter(Item::isNotEmpty)
                .filter(item -> item.isName(name))
                .findAny()
                .orElseThrow(() -> new VendingMachineException(ErrorMessage.INVALID_ITEM));

    }
}
