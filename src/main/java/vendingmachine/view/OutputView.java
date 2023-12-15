package vendingmachine.view;

import vendingmachine.dto.CoinsDto;

public class OutputView {

    public void printError(final String message) {
        System.out.println(System.lineSeparator() + message + System.lineSeparator());
    }

    public void printCoins(final CoinsDto coinsDto) {
        System.out.println(System.lineSeparator() + Response.HOLD_MONEY.value);
        coinsDto.getCoins()
                .forEach(coin -> System.out.printf(Response.COIN_COUNT.getValueForFormat(), coin.get(0), coin.get(1)));
        System.out.println();
    }

    public void printEnterMoney(final int money) {
        System.out.printf(Response.ENTER_MONEY.getValueForFormat(), money);
    }

    public void printRemainCoins(final CoinsDto remainCoinsDto) {
        System.out.println(Response.REMAIN_MONEY.value);
        remainCoinsDto.getCoins()
                .forEach(coin -> System.out.printf(Response.COIN_COUNT.getValueForFormat(), coin.get(0), coin.get(1)));
        System.out.println();
    }

    private enum Response {

        HOLD_MONEY("자판기가 보유한 동전"),
        COIN_COUNT("%d원 - %d개"),
        ENTER_MONEY("투입 금액: %d원"),
        REMAIN_MONEY("잔돈");

        private final String value;

        Response(final String value) {
            this.value = value;
        }

        public String getValueForFormat() {
            return value + System.lineSeparator();
        }
    }
}
