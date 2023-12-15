package vendingmachine.domain.item;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    private static List<Item> items = new ArrayList<>();

    public static void addItems(final List<Item> item) {
        items.addAll(item);
    }
}
