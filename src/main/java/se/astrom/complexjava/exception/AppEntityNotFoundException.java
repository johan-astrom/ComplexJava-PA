package se.astrom.complexjava.exception;

import javax.persistence.PersistenceException;

public class AppEntityNotFoundException extends PersistenceException {

    public AppEntityNotFoundException(String message) {
        super(message);
    }
}
