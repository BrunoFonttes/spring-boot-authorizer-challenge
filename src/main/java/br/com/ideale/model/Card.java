package br.com.ideale.model;

import br.com.ideale.exception.InsufficienteBalanceException;
import br.com.ideale.exception.InvalidPasswordException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.DatatypeConverter;
import lombok.Getter;
import lombok.SneakyThrows;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import java.security.MessageDigest;

@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Card {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The card number is required.")
    @Pattern(regexp = "^[0-9]*$")
    @Size(min = 13, max = 16)
    @Column(unique = true)
    @Getter
    private String cardNumber;

    @NotBlank(message = "The card password is required.")
    @Getter
    private String hashedPassword;

    @Min(0)
    @Getter
    private Float balance;

    @Version
    @Getter
    private long version;

    public Card() {
    }

    public Card(String cardNumber, String hashedPassword) {
        this.cardNumber = cardNumber;
        this.hashedPassword = this.getHashedPassword(hashedPassword);
        this.balance = (float) 500;
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
            assert Card.this.hashedPassword.equals(getHashedPassword(password)): new InvalidPasswordException();
        }

        public void consumeBalance(@Min(0) Float value) throws InsufficienteBalanceException {
           assert Card.this.balance - value > 0: new InsufficienteBalanceException();
            Card.this.setBalance(Card.this.balance - value);
        }
    }
}
