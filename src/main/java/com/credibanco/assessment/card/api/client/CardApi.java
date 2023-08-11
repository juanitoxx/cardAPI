package com.credibanco.assessment.card.api.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.controller.CardController;
import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.dto.CardDTOEnd;
import com.credibanco.assessment.card.dto.CardDTOEnroll;
import com.credibanco.assessment.card.dto.CardResponseDTO;
import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.util.ValidationUtil;

/**
 * Apis para operaciones RestFul de tarjetas
 *
 * @author jsbuitrago
 */
@RestController
@RequestMapping(
  value = "/card/",
  produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class CardApi {

  @Autowired private CardController cardController;

  @PostMapping
  public ResponseEntity<CardResponseDTO> processCard(
      @Validated @RequestBody final CardDTO cardDTO, final BindingResult bindingResult)
      throws ProcessException {
    ValidationUtil.validateBindingResult(bindingResult);
    return ResponseEntity.ok(cardController.createCardController(cardDTO));
  }

  @PutMapping
  public ResponseEntity<CardResponseDTO> updateStateCard(
      @Validated @RequestBody final CardDTOEnroll cardDTO, final BindingResult bindingResult)
      throws ProcessException {
    ValidationUtil.validateBindingResult(bindingResult);
    return ResponseEntity.ok(cardController.updateCardController(cardDTO));
  }

  @GetMapping
  public ResponseEntity<CardDTO> getCardInformation(
      @RequestParam(name = "PAN", required = true) final String pan) throws ProcessException {
    return ResponseEntity.ok(cardController.getCardController(pan));
  }

  @GetMapping("allCards")
  public ResponseEntity<List<CardDTOEnd>> getAllCards() throws ProcessException {
    return ResponseEntity.ok(cardController.findAllCards());
  }

  @DeleteMapping
  public ResponseEntity<CardResponseDTO> deleteCard(
      @Validated @RequestBody final CardDTOEnroll cardDTO, final BindingResult bindingResult)
      throws ProcessException {
    ValidationUtil.validateBindingResult(bindingResult);
    return ResponseEntity.ok(cardController.deleteCardController(cardDTO));
  }
}
