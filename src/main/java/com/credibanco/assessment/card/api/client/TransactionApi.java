package com.credibanco.assessment.card.api.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.controller.TransactionController;
import com.credibanco.assessment.card.dto.TransactionDTO;
import com.credibanco.assessment.card.dto.TransactionResponseDTO;
import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.util.ValidationUtil;

/**
 * Apis para operaciones RestFul de transacciones
 *
 * @author jsbuitrago
 */
@RestController
@RequestMapping(
  value = "/transaction/",
  produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class TransactionApi {

  @Autowired TransactionController transactionController;

  @PostMapping
  public ResponseEntity<TransactionResponseDTO> processCard(
      @Validated @RequestBody final TransactionDTO responseDTO, final BindingResult bindingResult)
      throws ProcessException {
    ValidationUtil.validateBindingResult(bindingResult);
    return ResponseEntity.ok(transactionController.createTransaction(responseDTO));
  }

  @PutMapping
  public ResponseEntity<TransactionResponseDTO> cancelTransaction(
      @Validated @RequestBody final TransactionDTO responseDTO, final BindingResult bindingResult)
      throws ProcessException {
    ValidationUtil.validateBindingResult(bindingResult);
    return ResponseEntity.ok(transactionController.cancelTransaction(responseDTO));
  }

  @GetMapping("allTransactions")
  public ResponseEntity<List<TransactionDTO>> getAllTransaction() throws ProcessException {
    return ResponseEntity.ok(transactionController.getAllTransaction());
  }
}
