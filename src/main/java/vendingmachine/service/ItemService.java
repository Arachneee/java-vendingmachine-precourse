package vendingmachine.service;

import vendingmachine.domain.Money;
import vendingmachine.domain.item.Item;
import vendingmachine.domain.item.ItemRepository;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class ItemService {


    public void buyItemByName(final String itemName, Money enterMoney) {
        Item item = ItemRepository.findByName(itemName);
        validateEnoughMoney(enterMoney, item);


    }

    private static void validateEnoughMoney(Money enterMoney, Item item) {
        if (enterMoney.isUnder(item.getMoney())) {
            throw new VendingMachineException(ErrorMessage.NOT_ENOUGH_ENTER_MONEY);
        }
    }
}
