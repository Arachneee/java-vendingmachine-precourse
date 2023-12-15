package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.Map;
import vendingmachine.Coin;

public class Coins {

    private Map<Coin, Integer> coins;

    private Coins(Map<Coin, Integer> coins) {
        this.coins = coins;
    }

    public static Coins from(final Money money) {
        Map<Coin, Integer> coins = new HashMap<>();
        while (true) {
            Coin coin = getRandomCoin();
            coins.put(coin, coins.getOrDefault(coin, 0) + 1);
            int coinsMoney = getCoinsMoney(coins);
            if (coinsMoney == money.getMoney()) {
                break;
            }
            if (coinsMoney > money.getMoney()) {
                coins = new HashMap<>();
            }
        }
        return new Coins(coins);
    }

    private static Coin getRandomCoin() {
        final int number = Randoms.pickNumberInList(Coin.numbers());
        return Coin.from(number);
    }

    private static int getCoinsMoney(final Map<Coin, Integer> coins) {
        return coins.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getAmount() * entry.getValue())
                .sum();
    }
}
