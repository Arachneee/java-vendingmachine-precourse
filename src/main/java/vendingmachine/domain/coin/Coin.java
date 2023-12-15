package vendingmachine.domain.coin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;


    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin from(final int amount) {
        return Arrays.stream(values())
                .filter(value -> value.amount == amount)
                .findAny()
                .orElseThrow(() -> new VendingMachineException(ErrorMessage.INVALID_COIN_NUMBER));
    }

    public static List<Integer> amounts() {
        return Arrays.stream(values())
                .map(value -> value.amount)
                .collect(Collectors.toList());
    }

    public int getAmount() {
        return amount;
    }
}
