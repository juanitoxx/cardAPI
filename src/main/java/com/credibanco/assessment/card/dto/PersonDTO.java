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
public class PersonDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long personId;
  private String fullName;
  private String identification;
  private String phoneNumber;
}
