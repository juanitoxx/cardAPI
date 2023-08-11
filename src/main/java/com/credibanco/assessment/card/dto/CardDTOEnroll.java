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
public class CardDTOEnroll implements Serializable {

  private static final long serialVersionUID = 6861462486650591703L;
  private String pan;
  private String validationNumber;
}
