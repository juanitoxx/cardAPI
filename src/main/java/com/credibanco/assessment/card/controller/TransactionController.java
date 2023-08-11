package com.credibanco.assessment.card.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.dto.CardTransactionDTO;
import com.credibanco.assessment.card.dto.TransactionDTO;
import com.credibanco.assessment.card.dto.TransactionResponseDTO;
import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.Transaction;
import com.credibanco.assessment.card.pattern.Translator;
import com.credibanco.assessment.card.service.CardService;
import com.credibanco.assessment.card.service.TransactionService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TransactionController {

  @Autowired private CardService cardService;

  @Autowired private TransactionService transactionService;

  @Autowired
  @Qualifier("cardEntityToDTO")
  private Translator<Card, CardDTO> cardEntityToDTO;

  @Autowired
  @Qualifier("transactionDTOToEntity")
  private Translator<TransactionDTO, Transaction> transactionDTOToEntity;

  @Autowired
  @Qualifier("transactionEntityToDTO")
  private Translator<Transaction, TransactionDTO> transactionEntityToDTO;

  public TransactionResponseDTO createTransaction(final TransactionDTO transactionDTO)
      throws ProcessException {
    try {

      Card card = cardService.findCarByPAN(transactionDTO.getPan());
      if (Objects.isNull(card)) {
        return TransactionResponseDTO.builder()
            .code("01")
            .message("Tarjeta no existe")
            .stateTransaction("RECHAZADA")
            .build();
      }
      card = cardService.findCardByPANAndState(transactionDTO.getPan(), "ENROLADA");
      if (Objects.nonNull(card)) {
        transactionDTO.setCardDTO(CardTransactionDTO.builder().cardId(card.getCardId()).build());
        transactionDTO.setStateTransaction("APROBADA");
        final Transaction transaction =
            transactionService.createTransaction(transactionDTOToEntity.translate(transactionDTO));
        return TransactionResponseDTO.builder()
            .code("00")
            .message("Compra exitosa")
            .stateTransaction(transaction.getStateTransaction())
            .build();
      }
      return TransactionResponseDTO.builder()
          .code("02")
          .message("Tarjeta no enrolada")
          .stateTransaction("RECHAZADA")
          .build();
    } catch (final ProcessException e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public TransactionResponseDTO cancelTransaction(final TransactionDTO transactionDTO)
      throws ProcessException {
    try {
      final Card card = cardService.findCarByPAN(transactionDTO.getPan());

      if (Objects.isNull(card)) {
        throw new ProcessException("La tarjeta no existe");
      }

      Transaction transaction =
          transactionService.findByReferenceNumber(transactionDTO.getReferenceNumber());
      if (Objects.isNull(transaction)) {
        return TransactionResponseDTO.builder()
            .code("01")
            .message("numero de referencia invalido")
            .referenceNumber(transactionDTO.getReferenceNumber())
            .build();
      }
      transaction =
          transactionService.findByPANAndPurchaseTotalAndReferenceNumber(
              card.getCardId(),
              transactionDTO.getPurchaseTotal(),
              transactionDTO.getReferenceNumber());
      if (verifyTransactionDate(transaction.getDateTransaction())) {
        return TransactionResponseDTO.builder()
            .code("02")
            .message("No se puede anular transacción")
            .referenceNumber(transaction.getReferenceNumber())
            .build();
      }
      transaction.setStateTransaction("ANULADA");
      transactionService.createTransaction(transaction);

      return TransactionResponseDTO.builder()
          .code("00")
          .message("Compra anulada")
          .referenceNumber(transaction.getReferenceNumber())
          .build();
    } catch (final ProcessException e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public List<TransactionDTO> getAllTransaction() throws ProcessException {
    final List<TransactionDTO> transactionDTOs = new ArrayList<>();
    try {
      final List<Transaction> transactions = transactionService.findAllTransaction();

      for (final Transaction transaction : transactions) {
        transactionDTOs.add(transactionEntityToDTO.translate(transaction));
      }

      return transactionDTOs;
    } catch (final ProcessException e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  private boolean verifyTransactionDate(final LocalDateTime dateTimeTransaction) {
    final LocalDateTime dateTimeNow = LocalDateTime.now();
    final long noOfMinutes = dateTimeTransaction.until(dateTimeNow, ChronoUnit.MINUTES);
    return noOfMinutes > 5;
  }
}
