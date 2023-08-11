package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTOEnd {
  private Long cardId;
  private String pan;
  private String maskedPan;
  private String cardType;
  private Long validationNumber;
  private String state;
  private PersonDTO personDTO;
}
