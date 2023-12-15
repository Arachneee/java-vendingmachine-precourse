package vendingmachine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public enum Coin {
    COIN_500(500, 1),
    COIN_100(100, 2),
    COIN_50(50, 3),
    COIN_10(10, 4);

    private final int amount;
    private final int number;

    Coin(final int amount, final int number) {
        this.amount = amount;
        this.number = number;
    }

    public static Coin from(final int number) {
        return Arrays.stream(values())
                .filter(value -> value.number == number)
                .findAny()
                .orElseThrow(() -> new VendingMachineException(ErrorMessage.INVALID_COIN_NUMBER));
    }

    public static List<Integer> numbers() {
        return Arrays.stream(values())
                .map(value -> value.number)
                .collect(Collectors.toList());
    }

    public int getAmount() {
        return amount;
    }
}
