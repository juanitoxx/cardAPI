package com.credibanco.assessment.card.translator;

import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Person;
import com.credibanco.assessment.card.pattern.Translator;

@Component
public class CardDTOToPerson implements Translator<CardDTO, Person> {

  @Override
  public Person translate(final CardDTO input) throws ProcessException {
    return Person.builder()
        .fullName(input.getFullName())
        .identification(input.getIdentification())
        .phoneNumber(input.getPhoneNumber())
        .build();
  }
}
