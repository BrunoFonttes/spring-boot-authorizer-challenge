package br.com.ideale.repository;

import br.com.ideale.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
    Card findByCardNumber(String cardNumber);
}