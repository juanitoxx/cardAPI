package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.model.Person;

/**
 * Servicios para personas
 *
 * @author jsbuitrago
 */
public interface PersonService {

  Person createPerson(Person person);

  Person findPersonByInformation(String identification, String phoneNumber, String fullName);
}
