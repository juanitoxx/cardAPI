package com.credibanco.assessment.card.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.card.model.Transaction;

/**
 * Repositorio para transacciones
 *
 * @author jsbuitrago
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
  @Query(
    value =
        "SELECT * FROM credibanco.transaction WHERE "
            + "PAN =:pan AND purchase_total =:purchaseTotal  AND reference_number =:referenceNumber",
    nativeQuery = true
  )
  Transaction findByPANAndPurchaseTotalAndReferenceNumber(
      @Param("pan") Long pan,
      @Param("purchaseTotal") String purchaseTotal,
      @Param("referenceNumber") String referenceNumber);

  @Query(
    value = "SELECT * FROM credibanco.transaction WHERE " + "reference_number =:referenceNumber",
    nativeQuery = true
  )
  Transaction findByReferenceNumber(@Param("referenceNumber") String referenceNumber);
}
