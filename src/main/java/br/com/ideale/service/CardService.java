package br.com.ideale.service;

import br.com.ideale.exception.CardAlreadyExistentException;
import br.com.ideale.exception.CardNotFoundException;
import br.com.ideale.model.Card;
import br.com.ideale.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public void newCard(Card card) {
        try {
            cardRepository.save(card);
        } catch (Exception error) {
            assert error instanceof DataIntegrityViolationException:new CardAlreadyExistentException();
            throw error;
        }
    }

    public Card getCardBy(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    public void newTransaction(String cardNumber, String password, Float value) {
        Card card = cardRepository.findByCardNumber(cardNumber);
        assert card != null: new CardNotFoundException();
        card
                .new AuthorizedOperations(password)
                .consumeBalance(value);

        this.cardRepository.save(card);
    }

}
