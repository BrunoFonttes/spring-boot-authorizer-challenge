package br.com.ideale.exception;

public class CardAlreadyExistentException extends RuntimeException {
    public CardAlreadyExistentException() {
        super("NAO_FOI_POSSIVEL_CADASTRAR_CARTAO");
    }
}
