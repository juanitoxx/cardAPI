package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardTransactionDTO implements Serializable {

  private static final long serialVersionUID = 1827567936599093528L;

  private Long cardId;
  private String pan;
  private Long cardType;
  private Long validationNumber;
  private String state;
}
