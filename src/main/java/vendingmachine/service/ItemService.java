package vendingmachine.service;

import java.util.List;
import vendingmachine.domain.Money;
import vendingmachine.domain.item.Item;
import vendingmachine.domain.item.ItemRepository;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItem(final List<Item> item) {
        itemRepository.addItems(item);
    }

    public boolean cantBayItem(final Money enterMoney) {
        try {
            final Money minMoney = itemRepository.getItemMinMoney();
            return enterMoney.isUnder(minMoney);
        } catch (VendingMachineException e) {
            return true;
        }
    }

    public void buyItemByName(final String itemName, Money enterMoney) {
        Item item = itemRepository.findByName(itemName);
        validateEnoughMoney(enterMoney, item);
        item.subCount();
        enterMoney.subMoney(item.getMoney());
    }

    private static void validateEnoughMoney(Money enterMoney, Item item) {
        if (enterMoney.isUnder(item.getMoney())) {
            throw new VendingMachineException(ErrorMessage.NOT_ENOUGH_ENTER_MONEY);
        }
    }
}
