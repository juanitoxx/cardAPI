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
public class CardResponseDTO implements Serializable {
  private static final long serialVersionUID = -4749950651111934060L;
  private String code;
  private String message;
  private Integer validationNumber;
  private String pan;
}
