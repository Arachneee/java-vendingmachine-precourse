package vendingmachine.controller;


import java.util.List;
import java.util.Optional;
import vendingmachine.domain.Money;
import vendingmachine.domain.coin.Coins;
import vendingmachine.domain.item.Item;
import vendingmachine.domain.item.ItemParser;
import vendingmachine.domain.item.ItemRepository;
import vendingmachine.dto.CoinsDto;
import vendingmachine.service.ItemService;
import vendingmachine.util.ExceptionRoofer;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ItemService itemService;

    public VendingMachineController(final InputView inputView, final OutputView outputView, final ItemService itemService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.itemService = itemService;
    }

    public void run() {
        Money holdingMoney = getHoldingMoney();
        Coins coins = Coins.from(holdingMoney);
        final CoinsDto coinsDto = CoinsDto.from(coins);
        outputView.printCoins(coinsDto);

        List<Item> item = getItems();
        ItemRepository.addItems(item);

        Money enterMoney = getEnterMoney();

        while (true) {
            outputView.printEnterMoney(enterMoney.getMoney());
            if (cantBayItem(enterMoney)) {
                Coins remainCoins = coins.getRemainCoins(enterMoney);
                final CoinsDto remainCoinsDto = CoinsDto.from(remainCoins);
                outputView.printRemainCoins(remainCoinsDto);
                break;
            }
            buyItem(enterMoney);
        }


    }

    private void buyItem(final Money enterMoney) {
        ExceptionRoofer.run(() -> {
            final String itemName = inputView.readItemName();
            itemService.buyItemByName(itemName, enterMoney);
        });
    }

    private static boolean cantBayItem(final Money enterMoney) {
        final Optional<Money> minMoney = ItemRepository.getItemMinMoney();
        return minMoney.filter(enterMoney::isUnder).isPresent();
    }

    private Money getEnterMoney() {
        return ExceptionRoofer.supply(() -> {
            String enterMoney = inputView.readEnterMoney();
            return new Money(enterMoney);
        });
    }

    private Money getHoldingMoney() {
        return ExceptionRoofer.supply(() -> {
            final String moneySource = inputView.readHoldingMoney();
            return new Money(moneySource);
        });
    }

    private List<Item> getItems() {
        return ExceptionRoofer.supply(() -> {
            final String itemsSource = inputView.readItem();
            return ItemParser.convertToItems(itemsSource);
        });
    }
}
