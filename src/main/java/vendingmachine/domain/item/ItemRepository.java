package vendingmachine.domain.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import vendingmachine.domain.Money;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class ItemRepository {

    private static List<Item> items = new ArrayList<>();

    public static void addItems(final List<Item> item) {
        items.addAll(item);
    }

    public static Optional<Money> getItemMinMoney() {
        return items.stream()
                .filter(Item::isNotEmpty)
                .map(Item::getMoney)
                .min(Money::compareTo);
    }

    public static Item findByName(final String name) {
        return items.stream()
                .filter(Item::isNotEmpty)
                .filter(item -> item.isName(name))
                .findAny()
                .orElseThrow(() -> new VendingMachineException(ErrorMessage.INVALID_ITEM));

    }
}
