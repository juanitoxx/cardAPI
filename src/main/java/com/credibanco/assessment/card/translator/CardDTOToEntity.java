package com.credibanco.assessment.card.translator;

import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.enums.CardTypeEnum;
import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.Person;
import com.credibanco.assessment.card.pattern.Translator;
import com.credibanco.assessment.card.util.RandomUtil;

/**
 * Clase para transformar dto a Entidad
 *
 * @author jsbuitrago
 */
@Component
public class CardDTOToEntity implements Translator<CardDTO, Card> {

  @Override
  public Card translate(final CardDTO input) throws ProcessException {

    return Card.builder()
        .cardType(CardTypeEnum.getCardType(input.getCardType()).getCode())
        .pan(input.getPan())
        .validationNumber(RandomUtil.generateValidationNumber().longValue())
        .state(input.getState())
        .person(
            Person.builder()
                .personId(input.getPersonId())
                .fullName(input.getFullName())
                .identification(input.getIdentification())
                .phoneNumber(input.getPhoneNumber())
                .build())
        .build();
  }
}
