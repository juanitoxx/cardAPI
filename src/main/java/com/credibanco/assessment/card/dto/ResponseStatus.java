package com.credibanco.assessment.card.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.credibanco.assessment.card.enums.ResponseStatusCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus implements Serializable {
  private static final long serialVersionUID = -4749950651111934060L;
  private ResponseStatusCode statusCode;
  private String message;
  private String status;
  private String detail;
  private String code;
  private LocalDateTime dateTime;
}
