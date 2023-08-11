package com.credibanco.assessment.card.translator;

import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.TransactionDTO;
import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Transaction;
import com.credibanco.assessment.card.pattern.Translator;
import com.credibanco.assessment.card.util.MaskedPANUtil;

@Component
public class TransactionEntityToDTO implements Translator<Transaction, TransactionDTO> {

  @Override
  public TransactionDTO translate(final Transaction input) throws ProcessException {
    return TransactionDTO.builder()
        .pan(MaskedPANUtil.getMaskedPAN(input.getPan().getPan(), '*'))
        .purchaseTotal(input.getPurchaseTotal())
        .purchasingAddress(input.getPurchasingAddress())
        .referenceNumber(input.getReferenceNumber())
        .stateTransaction(input.getStateTransaction())
        .dateTransaction(input.getDateTransaction())
        .build();
  }
}
