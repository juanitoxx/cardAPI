package com.credibanco.assessment.card.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatusCode {
  OK("OK", "00"),
  ERROR("FALLIDO", "01"),
  UNAUTHORIZED("UNAUTHORIZED", "03"),
  FORBIDDEN("FORBIDDEN", "04");

  private String status;
  private String code;
}
