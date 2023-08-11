package com.credibanco.assessment.card.service;

import java.util.List;

import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Card;

/**
 * Servicios para tarjetas
 *
 * @author jsbuitrago
 */
public interface CardService {

  Card createCardService(Card card, String state) throws ProcessException;

  Card findCardByPersonInformation(String pan, Long cardType, Long personId)
      throws ProcessException;

  Card findCarByPANAndValidationNumber(String pan, Long validationNumber) throws ProcessException;

  Card findCarByPAN(String pan) throws ProcessException;

  Integer deleteCardByPANAndValidationNumber(String pan, Long validationNumber)
      throws ProcessException;

  Card findCardByPANAndState(String pan, String state) throws ProcessException;

  List<Card> findAllCard() throws ProcessException;

  List<Card> findByPanAndCardType(String pan, String cardType) throws ProcessException;
}
