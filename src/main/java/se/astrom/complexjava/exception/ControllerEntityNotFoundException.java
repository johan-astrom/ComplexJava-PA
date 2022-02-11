package se.astrom.complexjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class ControllerEntityNotFoundException extends ResponseStatusException {

    public ControllerEntityNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
