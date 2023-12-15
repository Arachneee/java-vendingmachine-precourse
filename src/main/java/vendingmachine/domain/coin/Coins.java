package vendingmachine.domain.coin;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import vendingmachine.domain.Money;

public class Coins {

    private Map<Coin, Integer> coins;

    private Coins(Map<Coin, Integer> coins) {
        this.coins = coins;
    }

    public static Coins from(final Money money) {
        Map<Coin, Integer> coins = initCoins();
        while (true) {
            Coin coin = getRandomCoin();
            coins.put(coin, coins.get(coin) + 1);
            int coinsMoney = getCoinsMoney(coins);
            if (coinsMoney == money.getMoney()) {
                break;
            }
            if (coinsMoney > money.getMoney()) {
                coins = initCoins();
            }
        }
        return new Coins(coins);
    }

    private static Map<Coin, Integer> initCoins() {
        Map<Coin, Integer> coins = new HashMap<>();
        for (Coin coin : Coin.values()) {
            coins.put(coin, 0);
        }
        return coins;
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
                .map(this::getCoinCount)
                .collect(Collectors.toList());
    }

    private List<Integer> getCoinCount(final Entry<Coin, Integer> entry) {
        List<Integer> coinCount = new ArrayList<>();
        coinCount.add(entry.getKey().getAmount());
        coinCount.add(entry.getValue());
        return coinCount;
    }

    public Coins getRemainCoins(Money money) {
        Map<Coin, Integer> remainCoins = new HashMap<>();

        for (Coin coin : Coin.values()) {
            if (coins.get(coin) == 0 || coin.getAmount() > money.getMoney()) {
                continue;
            }
            int count = Math.min(coins.get(coin), money.getMoney() / coin.getAmount());
            money.subMoney(new Money(count * coin.getAmount()));
            remainCoins.put(coin, count);
        }
        return new Coins(remainCoins);
    }
}
