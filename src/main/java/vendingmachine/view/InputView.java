package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {


    public String readHoldingMoney() {
        System.out.println(Request.HOLDING_MONEY.value);
        return Console.readLine();
    }

    public String readItem() {
        System.out.println(Request.ITEM.value);
        return Console.readLine();
    }

    public String readEnterMoney() {
        System.out.println(System.lineSeparator() + Request.ENTER_MONEY.value);
        return Console.readLine();
    }

    public String readItemName() {
        System.out.println(Request.BUY_ITEM.value);
        return Console.readLine();
    }

    private enum Request {
        HOLDING_MONEY("자판기가 보유하고 있는 금액을 입력해 주세요."),
        ITEM("상품명과 가격, 수량을 입력해 주세요."),
        ENTER_MONEY("투입 금액을 입력해 주세요."),
        BUY_ITEM("구매할 상품명을 입력해 주세요.");
        private final String value;

        Request(final String value) {
            this.value = value;
        }
    }
}
