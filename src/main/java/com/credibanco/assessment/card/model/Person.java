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
 * Entidad para datos de personas
 *
 * @author jsbuitrago
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person", schema = "credibanco")
public class Person {
  @Id
  @Basic(optional = false)
  @Column(name = "person_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long personId;

  @Basic(optional = true)
  @Column(name = "full_name")
  private String fullName;

  @Basic(optional = true)
  @Column(name = "identification")
  private String identification;

  @Basic(optional = true)
  @Column(name = "phone_number")
  private String phoneNumber;
}
