package com.credibanco.assessment.card.translator;

import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.pattern.Translator;

/**
 * Clase para transformar Entidad a DTO
 *
 * @author jsbuitrago
 */
@Component
public class CardEntityToDTO implements Translator<Card, CardDTO> {

  @Override
  public CardDTO translate(final Card input) throws ProcessException {
    return CardDTO.builder()
        .pan(input.getPan())
        .fullName(input.getPerson().getFullName())
        .identification(input.getPerson().getIdentification())
        .phoneNumber(input.getPerson().getPhoneNumber())
        .state(input.getState())
        .build();
  }
}
