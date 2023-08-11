package com.credibanco.assessment.card.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.card.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

  @Query(
    value =
        "SELECT * FROM credibanco.person p "
            + "WHERE p.identification = ?1 "
            + "and p.phone_number = ?2 and p.full_name = ?3",
    nativeQuery = true
  )
  Person findPersonByInformation(String identification, String phoneNumber, String fullName);
}
