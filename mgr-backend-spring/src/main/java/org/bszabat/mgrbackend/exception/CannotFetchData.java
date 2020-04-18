package org.bszabat.mgrbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CannotFetchData extends RuntimeException {

    public CannotFetchData(String message) {
        super(message);
    }

    public CannotFetchData(String message, Throwable cause) {
        super(message, cause);
    }
}
