package com.credibanco.assessment.card.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.dto.CardDTOEnd;
import com.credibanco.assessment.card.dto.CardDTOEnroll;
import com.credibanco.assessment.card.dto.CardResponseDTO;
import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.Person;
import com.credibanco.assessment.card.pattern.Translator;
import com.credibanco.assessment.card.service.CardService;
import com.credibanco.assessment.card.service.PersonService;
import com.credibanco.assessment.card.util.MaskedPANUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CardController {

  @Autowired private CardService cardService;
  @Autowired private PersonService personService;

  @Autowired
  @Qualifier("cardDTOToEntity")
  private Translator<CardDTO, Card> cardDTOToEntity;

  @Autowired
  @Qualifier("cardDTOToPerson")
  private Translator<CardDTO, Person> cardDTOToPerson;

  @Autowired
  @Qualifier("cardEntityToDTO")
  private Translator<Card, CardDTO> cardEntityToDTO;

  @Autowired
  @Qualifier("cardEntityToCardDTOEnd")
  private Translator<Card, CardDTOEnd> cardEntityToCardDTOEnd;

  public CardResponseDTO createCardController(final CardDTO cardDTO) throws ProcessException {
    try {
      Person person =
          personService.findPersonByInformation(
              cardDTO.getIdentification(), cardDTO.getPhoneNumber(), cardDTO.getFullName());
      if (Objects.isNull(person)) {
        person = personService.createPerson(cardDTOToPerson.translate(cardDTO));
      }
      cardDTO.setPersonId(person.getPersonId());
      cardDTO.setState("CREADA");
      final Card card = cardService.createCardService(cardDTOToEntity.translate(cardDTO), "SAVE");
      return CardResponseDTO.builder()
          .code("00")
          .message("Exito")
          .pan(MaskedPANUtil.getMaskedPAN(card.getPan(), '*'))
          .validationNumber(card.getValidationNumber().intValue())
          .build();
    } catch (final ProcessException e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public CardResponseDTO updateCardController(final CardDTOEnroll cardDTO) throws ProcessException {
    try {

      Card card = cardService.findCarByPAN(cardDTO.getPan());
      if (Objects.isNull(card)) {
        return CardResponseDTO.builder()
            .code("01")
            .message("Tarjeta no existe")
            .pan(MaskedPANUtil.getMaskedPAN(cardDTO.getPan(), '*'))
            .build();
      }
      card =
          cardService.findCarByPANAndValidationNumber(
              cardDTO.getPan(), Long.parseLong(cardDTO.getValidationNumber()));
      if (Objects.isNull(card)) {
        return CardResponseDTO.builder()
            .code("02")
            .message("Numero de validacion invalido")
            .pan(MaskedPANUtil.getMaskedPAN(cardDTO.getPan(), '*'))
            .build();
      }

      card.setState("ENROLADA");
      cardService.createCardService(card, "UPDATE");

      return CardResponseDTO.builder()
          .code("00")
          .message("Exito")
          .pan(MaskedPANUtil.getMaskedPAN(cardDTO.getPan(), '*'))
          .build();

    } catch (final ProcessException e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public CardDTO getCardController(final String pan) throws ProcessException {
    try {
      final Card card = cardService.findCarByPAN(pan);
      if (Objects.nonNull(card)) {
        card.setPan(MaskedPANUtil.getMaskedPAN(card.getPan(), '*'));
        return cardEntityToDTO.translate(card);
      }
      return new CardDTO();
    } catch (final ProcessException e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public CardResponseDTO deleteCardController(final CardDTOEnroll cardDTOEnroll)
      throws ProcessException {
    try {
      final Integer response =
          cardService.deleteCardByPANAndValidationNumber(
              cardDTOEnroll.getPan(), Long.parseLong(cardDTOEnroll.getValidationNumber()));
      if (1 == response) {
        return CardResponseDTO.builder().code("00").message("Se ha eliminado la tarjeta").build();
      }

    } catch (final ProcessException e) {
      log.error(e.getMessage());
      throw e;
    }
    return CardResponseDTO.builder().code("01").message("No se ha eliminado la tarjeta").build();
  }

  public List<CardDTOEnd> findAllCards() throws ProcessException {
    try {
      final List<Card> cards = cardService.findAllCard();
      final List<CardDTOEnd> cardDTOEnds = new ArrayList<>();
      for (final Card card : cards) {
        cardDTOEnds.add(cardEntityToCardDTOEnd.translate(card));
      }
      return cardDTOEnds;
    } catch (final Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }
}
