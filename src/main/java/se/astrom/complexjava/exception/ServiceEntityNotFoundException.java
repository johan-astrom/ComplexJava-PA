package se.astrom.complexjava.exception;

import javax.persistence.PersistenceException;

public class ServiceEntityNotFoundException extends PersistenceException {

    public ServiceEntityNotFoundException(String message) {
        super(message);
    }
}
