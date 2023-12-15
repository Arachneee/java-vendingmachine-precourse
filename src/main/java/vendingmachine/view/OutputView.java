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

    private enum Response {

        HOLD_MONEY("자판기가 보유한 동전"),
        COIN_COUNT("%d원 - %d개");

        private final String value;

        Response(final String value) {
            this.value = value;
        }

        public String getValueForFormat() {
            return value + System.lineSeparator();
        }
    }
}
