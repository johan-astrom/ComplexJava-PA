package se.astrom.complexjava.exception;

public class AppAuthorizationException extends RuntimeException{

    public AppAuthorizationException(String message){
        super(message);
    }
}
