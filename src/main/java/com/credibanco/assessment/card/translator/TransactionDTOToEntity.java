package com.credibanco.assessment.card.translator;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.dto.TransactionDTO;
import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.Transaction;
import com.credibanco.assessment.card.pattern.Translator;

@Component
public class TransactionDTOToEntity implements Translator<TransactionDTO, Transaction> {

  @Autowired
  @Qualifier("cardDTOToEntity")
  private Translator<CardDTO, Card> cardDTOToEntity;

  @Override
  public Transaction translate(final TransactionDTO input) throws ProcessException {
    return Transaction.builder()
        .pan(Card.builder().cardId(input.getCardDTO().getCardId()).build())
        .stateTransaction(input.getStateTransaction())
        .referenceNumber(input.getReferenceNumber())
        .purchaseTotal(input.getPurchaseTotal())
        .purchasingAddress(input.getPurchasingAddress())
        .dateTransaction(LocalDateTime.now())
        .build();
  }
}
