package ictgradschool.web.lab13.ex2;

public class InvalidEntryException extends Exception {
    public InvalidEntryException() {
        super();
    }

    public InvalidEntryException(String message) {
        super(message);
    }

    public InvalidEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntryException(Throwable cause) {
        super(cause);
    }

    protected InvalidEntryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

