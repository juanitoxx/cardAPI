package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError implements Serializable {
  private static final long serialVersionUID = 4013939295816670168L;

  private String field;
  private String code;
  private String defaultMessage;
}
