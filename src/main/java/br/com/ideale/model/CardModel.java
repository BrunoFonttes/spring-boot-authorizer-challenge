package br.com.ideale.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Entity
@Data
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class CardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cardNumber;

    private String hashedPassword;

    private Float balance;

    @Version
    private long version;

    public CardModel(){}
}
