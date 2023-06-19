package br.com.ideale.model;

import br.com.ideale.exception.InsufficienteBalanceException;
import br.com.ideale.exception.InvalidPasswordException;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.DatatypeConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.security.MessageDigest;

@Entity
@Getter
@Setter
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The card number is required.")
    @Pattern(regexp = "^[0-9]*$")
    @Size(min = 13, max = 16)
    @Column(unique=true)
    private String cardNumber;

    @NotBlank(message = "The card password is required.")
    private String hashedPassword;

    @Min(0)
    private Float balance;

    @Version
    private long version;

    public Card(){}

    @SneakyThrows
    private String getHashedPassword(String password)  {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
       return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }
    public Card(String cardNumber, String hashedPassword) {
        this.cardNumber = cardNumber;
        this.hashedPassword = this.getHashedPassword(hashedPassword);
        this.balance = (float) 500;
    }

    private void authorize(String password){
        if(!this.hashedPassword.equals(getHashedPassword(password))) {
            throw new InvalidPasswordException();
        }
    }

   public void consumeBalance(String password , @Min(0) Float value) throws InsufficienteBalanceException{
        authorize(password);
        if (this.balance-value < 0){
            throw new InsufficienteBalanceException();
        }
        this.setBalance(this.balance - value);
    }
}
