package br.com.ideale.mappers;

import br.com.ideale.domain.Card;
import br.com.ideale.model.CardModel;

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
        if(card==null) return null;

        CardModel cardModel = new CardModel();
        cardModel.setId(card.getId());
        cardModel.setCardNumber(card.getCardNumber());
        cardModel.setHashedPassword(card.getHashedPassword());
        cardModel.setBalance(card.getBalance());
        return cardModel;
    }

    public static CardModel fromDomainToModel(CardModel cardModel, Card card){
        if(cardModel==null) return null;
        if(card == null) return cardModel;

        cardModel.setId(card.getId());
        cardModel.setCardNumber(card.getCardNumber());
        cardModel.setHashedPassword(card.getHashedPassword());
        cardModel.setBalance(card.getBalance());
        return cardModel;
    }
}
