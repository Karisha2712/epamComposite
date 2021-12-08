package edu.radyuk.compositetask.exception;

public class TextException extends Exception {

    public TextException() {
    }

    public TextException(String message) {
        super(message);
    }

    public TextException(Exception cause) {
        super(cause);
    }

    public TextException(String message, Exception cause) {
        super(message, cause);
    }
}
