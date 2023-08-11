package com.credibanco.assessment.card.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @Size(max = 19, min = 16)
  private String pan;

  @Size(max = 6, min = 6)
  private String referenceNumber;

  private String purchaseTotal;
  private String purchasingAddress;
  private String stateTransaction;
  private LocalDateTime dateTransaction;
  private CardTransactionDTO cardDTO;
}
