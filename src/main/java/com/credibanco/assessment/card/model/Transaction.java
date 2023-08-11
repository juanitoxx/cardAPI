package com.credibanco.assessment.card.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad para datos de transacciones
 *
 * @author jsbuitrago
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaction", schema = "credibanco")
public class Transaction {
  @Id
  @Basic(optional = false)
  @Column(name = "transaction_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionId;

  @Basic(optional = true)
  @Column(name = "reference_number")
  private String referenceNumber;

  @Basic(optional = true)
  @Column(name = "purchase_total")
  private String purchaseTotal;

  @Basic(optional = true)
  @Column(name = "purchasing_address")
  private String purchasingAddress;

  @Basic(optional = true)
  @Column(name = "date_transaction")
  private LocalDateTime dateTransaction;

  @Basic(optional = true)
  @Column(name = "state_transaction")
  private String stateTransaction;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "PAN", insertable = true, updatable = false)
  private Card pan;
}
