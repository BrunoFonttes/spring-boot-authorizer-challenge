package br.com.ideale.service;

import br.com.ideale.exception.CardAlreadyExistentException;
import br.com.ideale.exception.CardNotFoundException;
import br.com.ideale.domain.Card;
import br.com.ideale.mappers.CardMapper;
import br.com.ideale.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public void newCard(Card card) {
        try {
            cardRepository.save(CardMapper.fromDomainToModel(card));
        } catch (Exception error) {
            if(error instanceof DataIntegrityViolationException) {
                throw new CardAlreadyExistentException();
            }
            throw error;
        }
    }

    public Card getCardBy(String cardNumber) {
        return CardMapper.fromModelToDomain(cardRepository.findByCardNumber(cardNumber));
    }

    public void newTransaction(String cardNumber, String password, Float value) {
        Card card = CardMapper.fromModelToDomain(cardRepository.findByCardNumber(cardNumber));
        if(card == null) throw new CardNotFoundException();

        Card.AuthorizedOperations authorizedCard = card
                .new AuthorizedOperations(password);

        authorizedCard.consumeBalance(value);

        this.cardRepository.save(CardMapper.fromDomainToModel(card));
    }
}
