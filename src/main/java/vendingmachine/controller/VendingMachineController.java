package vendingmachine.controller;


import java.util.List;
import vendingmachine.domain.Money;
import vendingmachine.domain.RandomNumberGenerator;
import vendingmachine.domain.coin.Coins;
import vendingmachine.domain.item.Item;
import vendingmachine.domain.item.ItemParser;
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
        final Money holdingMoney = getHoldingMoney();
        Coins coins = Coins.createByRandom(holdingMoney, new RandomNumberGenerator());

        printCoins(coins);
        getItems();

        Money enterMoney = getEnterMoney();
        buyItemRoof(enterMoney, coins);
    }

    private Money getHoldingMoney() {
        return ExceptionRoofer.supply(() -> {
            final String moneySource = inputView.readHoldingMoney();
            return new Money(moneySource);
        });
    }

    private void printCoins(final Coins coins) {
        final CoinsDto coinsDto = CoinsDto.from(coins);
        outputView.printCoins(coinsDto);
    }

    private void getItems() {
        ExceptionRoofer.run(() -> {
            final String itemsSource = inputView.readItem();
            final List<Item> items = ItemParser.convertToItems(itemsSource);
            itemService.saveItem(items);
        });
    }

    private Money getEnterMoney() {
        return ExceptionRoofer.supply(() -> {
            String enterMoney = inputView.readEnterMoney();
            return new Money(enterMoney);
        });
    }

    private void buyItemRoof(Money enterMoney, Coins coins) {
        while (true) {
            outputView.printEnterMoney(enterMoney.getMoney());
            if (itemService.cantBayItem(enterMoney)) {
                Coins remainCoins = coins.getRemainCoins(enterMoney);
                printRemainCoins(remainCoins);
                break;
            }
            buyItem(enterMoney);
        }
    }

    private void printRemainCoins(Coins remainCoins) {
        final CoinsDto remainCoinsDto = CoinsDto.from(remainCoins);
        outputView.printRemainCoins(remainCoinsDto);
    }

    private void buyItem(final Money enterMoney) {
        ExceptionRoofer.run(() -> {
            final String itemName = inputView.readItemName();
            itemService.buyItemByName(itemName, enterMoney);
        });
    }
}
