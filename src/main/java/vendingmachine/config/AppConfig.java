package vendingmachine.config;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.domain.item.ItemRepository;
import vendingmachine.service.ItemService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public final class AppConfig {

    private AppConfig() {
    }

    public static VendingMachineController vendingMachineController() {
        return new VendingMachineController(new InputView(), new OutputView(), itemService());
    }

    private static ItemService itemService() {
        return new ItemService(new ItemRepository());
    }
}
