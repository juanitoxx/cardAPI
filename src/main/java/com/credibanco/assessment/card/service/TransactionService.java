package com.credibanco.assessment.card.service;

import java.util.List;

import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Transaction;

/**
 * Servicios para transacciones
 *
 * @author jsbuitrago
 */
public interface TransactionService {

  Transaction createTransaction(Transaction transaction) throws ProcessException;

  Transaction findByPANAndPurchaseTotalAndReferenceNumber(
      Long pan, String purchaseTotal, String referenceNumber) throws ProcessException;

  Transaction findByReferenceNumber(String referenceNumber) throws ProcessException;

  List<Transaction> findAllTransaction() throws ProcessException;
}
