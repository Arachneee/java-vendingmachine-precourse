package vendingmachine.dto;

import java.util.List;
import vendingmachine.domain.Coins;

public class CoinsDto {

    private final List<List<Integer>> coins;

    private CoinsDto(List<List<Integer>> coins) {
        this.coins = coins;
    }

    public static CoinsDto from(final Coins coins) {
        return new CoinsDto(coins.getCoinCount());
    }

    public List<List<Integer>> getCoins() {
        return coins;
    }
}
