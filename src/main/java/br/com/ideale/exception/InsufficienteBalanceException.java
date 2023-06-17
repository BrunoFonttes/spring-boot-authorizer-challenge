package br.com.ideale.exception;

public class InsufficienteBalanceException extends RuntimeException{
    public InsufficienteBalanceException(){
        super("SALDO_INSUFICIENTE");
    }
}