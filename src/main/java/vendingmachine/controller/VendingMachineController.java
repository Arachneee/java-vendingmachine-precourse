package vendingmachine.controller;


import vendingmachine.domain.Coins;
import vendingmachine.domain.Money;
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

    }

    private Money getHoldingMoney() {
        return ExceptionRoofer.supply(() -> {
            String moneySource = inputView.readHoldingMoney();
            return new Money(moneySource);
        });
    }
}
