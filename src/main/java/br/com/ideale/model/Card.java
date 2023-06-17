package br.com.ideale.model;

import br.com.ideale.exception.InsufficienteBalanceException;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Entity
public class Card {

    @NotBlank(message = "The card number is required.")
    @Pattern(regexp = "^\\d{10}$")
    @Size(min = 13, max = 16)
    @Getter
    String number;

    @NotBlank(message = "The card password is required.")
    @Size(min = 5, max = 10)
    private final String password;

    @NotNull
    @Size(min=0)
    @Getter
    private Float balance;

    public Card(String number, String password){
        this.number = number;
        this.password = password;
        this.balance = (float)500;
    }

    public Card(String number, String password, @NotNull Float balance){
        this.number = number;
        this.password = password;
        this.balance = balance;
    }

    public void makeTransaction(@Min(0) Float value){
        if (this.balance-value < 0){
            throw new InsufficienteBalanceException();
        }
        this.balance -= value;
    }
}
