package vendingmachine.exception;

public class VendingMachineException extends IllegalArgumentException {

    public VendingMachineException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
