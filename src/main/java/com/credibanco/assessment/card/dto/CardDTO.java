package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO implements Serializable {

  private static final long serialVersionUID = 6861462486650591703L;

  @Size(max = 19, min = 16)
  private String pan;

  private String fullName;

  @Size(max = 15, min = 10)
  private String identification;

  private String cardType;

  @Size(max = 10, min = 10)
  private String phoneNumber;

  private Long personId;

  private String state;
}
