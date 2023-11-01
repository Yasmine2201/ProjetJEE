package fr.efrei.teachfinder.exceptions;

public class UnavailableLoginException extends Exception {
    public UnavailableLoginException() {
    }

    public UnavailableLoginException(String message) {
        super(message);
    }

    public UnavailableLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavailableLoginException(Throwable cause) {
        super(cause);
    }

    public UnavailableLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
