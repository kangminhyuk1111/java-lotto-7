package lotto.ui.exception;

public class InputException extends NumberFormatException {

    public InputException(final InputExceptionMessage message) {
        super(message.getErrorMessage());
    }
}