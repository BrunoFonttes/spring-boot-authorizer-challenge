package br.com.ideale.mappers;

import br.com.ideale.domain.Card;
import br.com.ideale.exception.InternalExceptionHandler;
import br.com.ideale.model.CardModel;

import java.util.Objects;

public class CardMapper {

    public static Card fromModelToDomain(CardModel cardModel){
        if(cardModel==null) return null;

        return new Card(cardModel.getId(),
                cardModel.getCardNumber(),
                cardModel.getHashedPassword(),
                cardModel.getBalance()
        );
    }
    public static CardModel fromDomainToModel(Card card){
        return CardMapper.fromDomainToModel(card, new CardModel());
    }



    public static CardModel fromDomainToModel(Card card, CardModel cardModel){
        if(cardModel==null){ throw new InternalExceptionHandler("card model should not be null"); }
        if(card == null) return cardModel;
        if(card.getId()!=null && !Objects.equals(card.getId(), cardModel.getId())){
            throw new InternalExceptionHandler("card model id and card id must be equal");
        }
        cardModel.setCardNumber(card.getCardNumber());
        cardModel.setHashedPassword(card.getHashedPassword());
        cardModel.setBalance(card.getBalance());
        return cardModel;
    }
}
