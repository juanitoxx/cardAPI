package com.credibanco.assessment.card.model;

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
 * Entidad para tarjetas
 *
 * @author jsbuitrago
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "card", schema = "credibanco")
public class Card {
  @Id
  @Basic(optional = false)
  @Column(name = "card_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardId;

  @Basic(optional = true)
  @Column(name = "PAN")
  private String pan;

  @Basic(optional = true)
  @Column(name = "card_type")
  private Long cardType;

  @Basic(optional = true)
  @Column(name = "validation_number")
  private Long validationNumber;

  @Basic(optional = true)
  @Column(name = "state")
  private String state;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "person_id", insertable = true, updatable = false)
  private Person person;
}
