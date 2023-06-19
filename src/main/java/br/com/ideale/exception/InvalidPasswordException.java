package br.com.ideale.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(){
        super("SENHA_INVALIDA");
    }
}
