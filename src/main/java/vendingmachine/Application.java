package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.service.ItemService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {
    public static void main(String[] args) {
        VendingMachineController vendingMachineController = new VendingMachineController(new InputView(), new OutputView(), new ItemService());
        vendingMachineController.run();
    }
}
