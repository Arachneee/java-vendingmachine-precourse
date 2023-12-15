package vendingmachine.domain.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import vendingmachine.domain.Money;

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
}
