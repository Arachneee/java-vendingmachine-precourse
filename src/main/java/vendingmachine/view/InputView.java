package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {


    public String readHoldingMoney() {
        System.out.println(Request.HOLDING_MONEY.value);
        return Console.readLine();
    }

    private enum Request {
        HOLDING_MONEY("자판기가 보유하고 있는 금액을 입력해 주세요.");
        private final String value;

        Request(final String value) {
            this.value = value;
        }
    }
}
