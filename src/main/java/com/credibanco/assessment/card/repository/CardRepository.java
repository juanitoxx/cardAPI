package com.credibanco.assessment.card.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.card.model.Card;

/**
 * Repositorio para tarjetas
 *
 * @author jsbuitrago
 */
@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

  @Query(
    value =
        "SELECT * FROM credibanco.card WHERE "
            + "PAN =:pan AND card_type =:cardType AND person_id =:personId",
    nativeQuery = true
  )
  Card findCardByPersonInformation(
      @Param("pan") String pan, @Param("cardType") Long cardType, @Param("personId") Long personId);

  @Query(
    value =
        "SELECT * FROM credibanco.card WHERE "
            + "PAN =:pan AND validation_number =:validationNumber",
    nativeQuery = true
  )
  Card findCarByPANAndValidationNumber(
      @Param("pan") String pan, @Param("validationNumber") Long validationNumber);

  @Query(value = "SELECT * FROM credibanco.card WHERE PAN =:pan ", nativeQuery = true)
  Card findCarByPAN(@Param("pan") String pan);

  @Query(
    value = "SELECT * FROM credibanco.card WHERE PAN =:pan AND state =:state",
    nativeQuery = true
  )
  Card findCardByPANAndState(@Param("pan") String pan, @Param("state") String state);

  List<Card> findByPanAndCardType(String pan, String cardType);
}
