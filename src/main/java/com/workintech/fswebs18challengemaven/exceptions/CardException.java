package com.workintech.fswebs18challengemaven.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CardException extends RuntimeException {

    private HttpStatus httpStatus;

    public CardException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public CardException(String message) {
        super(message);
        this.httpStatus = determineHttpStatus(message);
    }

    private HttpStatus determineHttpStatus(String message) {
        if (message.contains("not found")) {
            return HttpStatus.NOT_FOUND;
        } else if (message.contains("invalid")) {
            return HttpStatus.BAD_REQUEST;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
