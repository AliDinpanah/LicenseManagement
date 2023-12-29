package teck.me.license.exception;

import org.springframework.http.HttpStatus;

public class ExceptionsPattern {
    private final String message;
    private final HttpStatus httpStatus;
    private final String code;

    public ExceptionsPattern(String message, HttpStatus httpStatus, String code) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
