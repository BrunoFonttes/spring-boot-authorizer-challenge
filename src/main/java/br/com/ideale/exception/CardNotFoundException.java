package br.com.ideale.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException() {
        super("CARTAO_NAO_CADASTRADO");
    }
}
