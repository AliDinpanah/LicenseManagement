package teck.me.license.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> notFoundException(NotFoundException e) {
        ExceptionsPattern exceptionsPattern = new ExceptionsPattern(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,"Not Found code");
        return new ResponseEntity<>(exceptionsPattern, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<Object> conflictException(ConflictException e) {
        ExceptionsPattern exceptionsPattern = new ExceptionsPattern(e.getMessage(), HttpStatus.CONFLICT,"Conflict code");
        return new ResponseEntity<>(exceptionsPattern, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {DataLogicException.class})
    public ResponseEntity<Object> validationException(DataLogicException e) {
        ExceptionsPattern exceptionsPattern = new ExceptionsPattern(e.getMessage(), HttpStatus.BAD_REQUEST,"Validation code");
        return new ResponseEntity<>(exceptionsPattern, HttpStatus.BAD_REQUEST);
    }
}
