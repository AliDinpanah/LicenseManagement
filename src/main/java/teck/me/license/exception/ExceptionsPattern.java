package teck.me.license.exception;

import org.springframework.http.HttpStatus;

public class ExceptionsPattern {
    private final String message;
    private final HttpStatus httpStatus;

    public ExceptionsPattern(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
