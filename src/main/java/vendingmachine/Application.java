package vendingmachine;

import vendingmachine.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig.vendingMachineController()
                .run();
    }
}
