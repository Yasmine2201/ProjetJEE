package fr.efrei.teachfinder.exceptions;

public class IncompleteEntityException extends Exception {
    public IncompleteEntityException() {
    }

    public IncompleteEntityException(String message) {
        super(message);
    }

    public IncompleteEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompleteEntityException(Throwable cause) {
        super(cause);
    }

    public IncompleteEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
