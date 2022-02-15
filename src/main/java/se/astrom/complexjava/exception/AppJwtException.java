package se.astrom.complexjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AppJwtException extends ResponseStatusException {

    public AppJwtException(HttpStatus httpStatus, String message){
        super(httpStatus, message);
    }
}
