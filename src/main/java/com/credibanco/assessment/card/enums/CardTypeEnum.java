package com.credibanco.assessment.card.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Constantes para tipos de tarjetas
 *
 * @author jsbuitrago
 */
@Getter
@AllArgsConstructor
public enum CardTypeEnum {
  CREDIT("CREDITO", 1L),
  DEBIT("DEBITO", 2L);
  private String cardType;
  private Long code;

  public static CardTypeEnum getCardType(final String cardType) {
    for (final CardTypeEnum cardTypeEnum : CardTypeEnum.values()) {
      if (Objects.equals(cardTypeEnum.getCardType(), cardType)) {
        return cardTypeEnum;
      }
    }
    return null;
  }

  public static CardTypeEnum getCardCode(final Long cardCode) {
    for (final CardTypeEnum cardTypeEnum : CardTypeEnum.values()) {
      if (Objects.equals(cardTypeEnum.code, cardCode)) {
        return cardTypeEnum;
      }
    }
    return null;
  }
}
