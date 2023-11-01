package fr.efrei.teachfinder.exceptions;

public class InvalidRegistrationException extends Exception {
    public InvalidRegistrationException() {
    }

    public InvalidRegistrationException(String message) {
        super(message);
    }

    public InvalidRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRegistrationException(Throwable cause) {
        super(cause);
    }

    public InvalidRegistrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
