package teck.me.license.exception;


import javax.validation.ValidationException;

public class DataLogicException extends ValidationException {
    public DataLogicException(String message) {
        super(message);
    }
}
