package com.credibanco.assessment.card.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad para datos de tipo de tarjetas
 *
 * @author jsbuitrago
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cardType", schema = "credibanco")
public class CardType {

  @Id
  @Basic(optional = false)
  @Column(name = "card_type_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardTypeId;

  @Basic(optional = true)
  @Column(name = "card_type")
  private String cardTypeName;
}
