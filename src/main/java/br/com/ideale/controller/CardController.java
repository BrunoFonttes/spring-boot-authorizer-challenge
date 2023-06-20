package br.com.ideale.controller;

import br.com.ideale.dto.CardDTO;
import br.com.ideale.dto.NewCardDTO;
import br.com.ideale.dto.TransactionDTO;
import br.com.ideale.exception.CardAlreadyExistentException;
import br.com.ideale.exception.CardNotFoundException;
import br.com.ideale.model.Card;
import br.com.ideale.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/cartoes")
    @ResponseStatus(HttpStatus.CREATED)
    NewCardDTO newCard(@Valid @RequestBody NewCardDTO newCardDTO) {
        Card newCard = new Card(newCardDTO.getNumeroCartao(), newCardDTO.getSenha());
        cardService.newCard(newCard);
        return newCardDTO;
    }

    @GetMapping("/cartoes/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    CardDTO getCardBy(@PathVariable String cardNumber) {
        Card card =  this.cardService.getCardBy(cardNumber);
        return new CardDTO(card.getBalance());
    }

    @PostMapping("/transacoes")
    @ResponseStatus(HttpStatus.CREATED)
    void newTransaction(@Valid @RequestBody TransactionDTO newTransactionDTO) {
         this.cardService.newTransaction(newTransactionDTO.getNumeroCartao(),
                newTransactionDTO.getSenhaCartao(),
                newTransactionDTO.getValor());
    }
}
