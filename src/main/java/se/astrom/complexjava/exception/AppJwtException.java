package se.astrom.complexjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AppJwtException extends ResponseStatusException {

    public AppJwtException(HttpStatus httpStatus, String message){
        super(httpStatus, message);
    }
}
