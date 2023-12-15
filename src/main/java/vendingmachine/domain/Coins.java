package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
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

    public List<List<Integer>> getCoinCount() {
        return coins.entrySet().stream()
                .map(Coins::getCoinCount)
                .collect(Collectors.toList());
    }

    private static List<Integer> getCoinCount(final Entry<Coin, Integer> entry) {
        List<Integer> coinCount = new ArrayList<>();
        coinCount.add(entry.getKey().getAmount());
        coinCount.add(entry.getValue());
        return coinCount;
    }
}
