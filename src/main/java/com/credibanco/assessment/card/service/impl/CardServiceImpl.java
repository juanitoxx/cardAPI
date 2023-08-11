package com.credibanco.assessment.card.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.repository.CardRepository;
import com.credibanco.assessment.card.service.CardService;

import lombok.extern.log4j.Log4j2;

/**
 * Servicios para tarjetas
 *
 * @author jsbuitrago
 */
@Component
@Log4j2
public class CardServiceImpl implements CardService {

  @Autowired CardRepository cardRepository;

  @Override
  public Card createCardService(final Card card, final String state) {
    try {

      if (state.equals("UPDATE")) {
        return cardRepository.save(card);
      }
      final Card cardQuery =
          findCardByPersonInformation(
              card.getPan(), card.getCardType(), card.getPerson().getPersonId());
      if (Objects.isNull(cardQuery)) {
        return cardRepository.save(card);
      }

      return cardQuery;

    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }

  @Override
  public List<Card> findByPanAndCardType(final String pan, final String cardType) {
    try {
      return cardRepository.findByPanAndCardType(pan, cardType);
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }

  @Override
  public Card findCardByPersonInformation(
      final String pan, final Long cardType, final Long personId) {
    try {
      return cardRepository.findCardByPersonInformation(pan, cardType, personId);
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }

  @Override
  public Card findCarByPANAndValidationNumber(final String pan, final Long validationNumber) {
    try {
      return cardRepository.findCarByPANAndValidationNumber(pan, validationNumber);
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }

  @Override
  public Card findCarByPAN(final String pan) {
    try {
      return cardRepository.findCarByPAN(pan);
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }

  @Override
  public Integer deleteCardByPANAndValidationNumber(final String pan, final Long validationNumber)
      throws ProcessException {
    try {
      final Card card = findCarByPANAndValidationNumber(pan, validationNumber);
      if (Objects.nonNull(card)) {
        cardRepository.delete(card);
        return 1;
      }
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
    return 0;
  }

  @Override
  public Card findCardByPANAndState(final String pan, final String state) throws ProcessException {
    try {
      return cardRepository.findCardByPANAndState(pan, state);
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }

  @Override
  public List<Card> findAllCard() throws ProcessException {
    try {
      return (List<Card>) cardRepository.findAll();
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }
}
