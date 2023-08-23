package com.credibanco.assessment.card.api.client;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import com.credibanco.assessment.card.controller.TransactionController;
import com.credibanco.assessment.card.dto.CardTransactionDTO;
import com.credibanco.assessment.card.dto.TransactionDTO;
import com.credibanco.assessment.card.exceptions.ProcessException;

@RunWith(MockitoJUnitRunner.class)
public class TransactionApiTest {

  @InjectMocks private TransactionApi transactionApi;
  @Mock private TransactionController transactionController;
  @Mock private BindingResult bindingResult;
  private CardTransactionDTO cardDTO;
  private TransactionDTO responseDTO;

  @Before
  public void setUp() {
    cardDTO =
        CardTransactionDTO.builder()
            .cardType(1L)
            .pan("1230217898000000")
            .validationNumber(1L)
            .state("E")
            .build();
    responseDTO =
        TransactionDTO.builder()
            .dateTransaction(LocalDateTime.now())
            .cardDTO(cardDTO)
            .pan("1230217898000000")
            .stateTransaction("E")
            .purchasingAddress("cra 198")
            .referenceNumber("102120")
            .purchaseTotal("10000")
            .build();
  }

  @Test
  public void processCard() throws ProcessException {
    assertNotNull(transactionApi.processCard(responseDTO, bindingResult));
  }

  @Test
  public void cancelTransaction() throws ProcessException {
    assertNotNull(transactionApi.cancelTransaction(responseDTO, bindingResult));
  }

  @Test
  public void getAllTransaction() throws ProcessException {
    assertNotNull(transactionApi.getAllTransaction());
  }
}
