package br.com.ideale.exception;

public class InternalExceptionHandler extends RuntimeException {
    public InternalExceptionHandler(String message) {
        super(message);
    }
}
