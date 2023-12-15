package vendingmachine.view;

public class OutputView {

    public void printError(final String message) {
        System.out.println(System.lineSeparator() + message + System.lineSeparator());
    }

    private enum Response {
        MONEY("%,d"),
        PERCENT("%,.1f%%");

        private final String value;

        Response(final String value) {
            this.value = value;
        }
    }
}
