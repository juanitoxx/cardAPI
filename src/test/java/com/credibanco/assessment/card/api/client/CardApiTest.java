package com.credibanco.assessment.card.api.client;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import com.credibanco.assessment.card.controller.CardController;
import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.dto.CardDTOEnroll;
import com.credibanco.assessment.card.exceptions.ProcessException;

@RunWith(MockitoJUnitRunner.class)
public class CardApiTest {
  @Mock private CardController cardController;
  @InjectMocks CardApi cardApi;
  @Mock private BindingResult bindingResult;

  private CardDTO cardDTO;
  private CardDTOEnroll cardDTOEnroll;

  @Before
  public void setUp() {
    cardDTOEnroll =
        CardDTOEnroll.builder().pan("1230217898000001").validationNumber("asdfasdfsaf").build();
    cardDTO =
        CardDTO.builder()
            .cardType("test")
            .fullName("Name")
            .pan("1230217898000000")
            .identification("101023")
            .phoneNumber("321485622")
            .build();
  }

  @Test
  public void processCard() throws ProcessException {
    assertNotNull(cardApi.processCard(cardDTO, bindingResult));
  }

  @Test
  public void updateStateCard() throws ProcessException {
    assertNotNull(cardApi.updateStateCard(cardDTOEnroll, bindingResult));
  }

  @Test
  public void getCardInformation() throws ProcessException {
    assertNotNull(cardApi.getCardInformation("123458989963d00"));
  }

  @Test
  public void getAllCards() throws ProcessException {
    assertNotNull(cardApi.getAllCards());
  }

  @Test
  public void deleteCard() throws ProcessException {
    assertNotNull(cardApi.deleteCard(cardDTOEnroll, bindingResult));
  }
}
