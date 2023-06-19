package br.com.ideale.controller;

import br.com.ideale.dto.CardDTO;
import br.com.ideale.dto.NewCardDTO;
import br.com.ideale.dto.TransactionDTO;
import br.com.ideale.exception.CardAlreadyExistentException;
import br.com.ideale.exception.CardNotFoundException;
import br.com.ideale.model.Card;
import br.com.ideale.repository.CardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/cartoes")
    @ResponseStatus(HttpStatus.CREATED)
    NewCardDTO newCard(@Valid @RequestBody NewCardDTO newCardDTO) {
        try {
            Card newCard = new Card(newCardDTO.getNumeroCartao(), newCardDTO.getSenha());
            cardRepository.save(newCard);
            return newCardDTO;
         }catch (Exception error){
            if(error instanceof DataIntegrityViolationException){
                throw new CardAlreadyExistentException();
            }
            throw error;
        }
    }

    @GetMapping("/cartoes/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    CardDTO getCardBy(@PathVariable String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber);
        return new CardDTO(card.getBalance());
    }

    @PostMapping("/transacoes")
    @ResponseStatus(HttpStatus.CREATED)
    void newTransaction(@Valid @RequestBody TransactionDTO newTransactionDTO) {
        Card card = cardRepository.findByCardNumber(newTransactionDTO.getNumeroCartao());
        if(card == null){
            throw new CardNotFoundException();
        }
        card.consumeBalance(newTransactionDTO.getSenhaCartao(),newTransactionDTO.getValor());
        this.cardRepository.save(card);
    }
}
