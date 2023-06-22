package br.com.ideale.domain;

import br.com.ideale.exception.InsufficienteBalanceException;
import br.com.ideale.exception.InvalidPasswordException;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.DatatypeConverter;
import lombok.Getter;
import lombok.SneakyThrows;
import java.security.MessageDigest;

@Getter
public class Card {

    private Long id;

    @NotBlank(message = "The card number is required.")
    @Pattern(regexp = "^[0-9]*$")
    @Size(min = 13, max = 16)
    private final String cardNumber;

    @NotBlank(message = "The card password is required.")
    private final String hashedPassword;

    @NotNull
    @Min(0)
    private Float balance;

    public Card(String cardNumber, String hashedPassword) {
        this.cardNumber = cardNumber;
        this.hashedPassword = this.getHashedPassword(hashedPassword);
        this.balance = (float) 500;
    }

    public Card(long id,String cardNumber, String hashedPassword,Float balance ){
        this.id = id;
        this.cardNumber = cardNumber;
        this.hashedPassword = hashedPassword;
        this.balance = balance;
    }

    @SneakyThrows
    private String getHashedPassword(String password) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }

    public class AuthorizedOperations {
        public AuthorizedOperations(String password) {
            if(!Card.this.hashedPassword.equals(getHashedPassword(password))) {
                throw new InvalidPasswordException();
            }
        }

        public void consumeBalance(@Min(0) Float value) {
            if (Card.this.balance - value < 0) throw new InsufficienteBalanceException();
            Card.this.balance -= value;
        }
    }
}
