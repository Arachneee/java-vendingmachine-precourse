package vendingmachine.service;

import vendingmachine.domain.item.Item;
import vendingmachine.domain.item.ItemRepository;

public class ItemService {


    public void buyItemByName(final String itemName) {
        Item item = ItemRepository.findByName(itemName);

    }
}
