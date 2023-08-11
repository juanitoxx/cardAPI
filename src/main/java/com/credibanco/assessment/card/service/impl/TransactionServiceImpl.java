package com.credibanco.assessment.card.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Transaction;
import com.credibanco.assessment.card.repository.TransactionRepository;
import com.credibanco.assessment.card.service.TransactionService;

import lombok.extern.log4j.Log4j2;

/**
 * Implementacion de servicios para transacciones
 *
 * @author jsbuitrago
 */
@Service
@Log4j2
public class TransactionServiceImpl implements TransactionService {

  @Autowired private TransactionRepository repository;

  @Override
  public Transaction createTransaction(final Transaction transaction) throws ProcessException {
    try {
      return repository.save(transaction);
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }

  @Override
  public Transaction findByPANAndPurchaseTotalAndReferenceNumber(
      final Long pan, final String purchaseTotal, final String referenceNumber) {
    try {
      return repository.findByPANAndPurchaseTotalAndReferenceNumber(
          pan, purchaseTotal, referenceNumber);
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }

  @Override
  public Transaction findByReferenceNumber(final String referenceNumber) throws ProcessException {
    try {
      return repository.findByReferenceNumber(referenceNumber);
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }

  @Override
  public List<Transaction> findAllTransaction() throws ProcessException {
    try {
      return (List<Transaction>) repository.findAll();
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }
}
