package vendingmachine.controller;


import java.util.List;
import vendingmachine.domain.Money;
import vendingmachine.domain.coin.Coins;
import vendingmachine.domain.item.Item;
import vendingmachine.domain.item.ItemParser;
import vendingmachine.domain.item.ItemRepository;
import vendingmachine.dto.CoinsDto;
import vendingmachine.util.ExceptionRoofer;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money money = getHoldingMoney();
        Coins coins = Coins.from(money);
        final CoinsDto coinsDto = CoinsDto.from(coins);
        outputView.printCoins(coinsDto);
        List<Item> item = getItems();
        ItemRepository.addItems(item);
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
