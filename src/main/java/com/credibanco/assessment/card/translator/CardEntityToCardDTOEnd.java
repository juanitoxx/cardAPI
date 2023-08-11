package com.credibanco.assessment.card.translator;

import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.CardDTOEnd;
import com.credibanco.assessment.card.dto.PersonDTO;
import com.credibanco.assessment.card.enums.CardTypeEnum;
import com.credibanco.assessment.card.exceptions.ProcessException;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.pattern.Translator;
import com.credibanco.assessment.card.util.MaskedPANUtil;

@Component
public class CardEntityToCardDTOEnd implements Translator<Card, CardDTOEnd> {

  @Override
  public CardDTOEnd translate(final Card input) throws ProcessException {
    return CardDTOEnd.builder()
        .cardId(input.getCardId())
        .cardType(CardTypeEnum.getCardCode(input.getCardType()).getCardType())
        .pan(input.getPan())
        .maskedPan(MaskedPANUtil.getMaskedPAN(input.getPan(), '*'))
        .validationNumber(input.getValidationNumber())
        .state(input.getState())
        .personDTO(
            PersonDTO.builder()
                .fullName(input.getPerson().getFullName())
                .identification(input.getPerson().getIdentification())
                .personId(input.getPerson().getPersonId())
                .phoneNumber(input.getPerson().getPhoneNumber())
                .build())
        .build();
  }
}
