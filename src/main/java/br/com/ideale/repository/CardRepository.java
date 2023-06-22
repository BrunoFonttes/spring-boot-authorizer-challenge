package br.com.ideale.repository;

import br.com.ideale.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardModel, Long> {
    CardModel findByCardNumber(String cardNumber);
}