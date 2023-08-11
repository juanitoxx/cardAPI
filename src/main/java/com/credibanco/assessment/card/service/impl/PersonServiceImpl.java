package com.credibanco.assessment.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.model.Person;
import com.credibanco.assessment.card.repository.PersonRepository;
import com.credibanco.assessment.card.service.PersonService;

import lombok.extern.log4j.Log4j2;

/**
 * Implementacion de servicios para personas
 *
 * @author jsbuitrago
 */
@Log4j2
@Service
public class PersonServiceImpl implements PersonService {

  @Autowired PersonRepository personRepository;

  @Override
  public Person createPerson(final Person person) {
    try {
      return personRepository.save(person);
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }

  @Override
  public Person findPersonByInformation(
      final String identification, final String phoneNumber, final String fullName) {
    try {
      return personRepository.findPersonByInformation(identification, phoneNumber, fullName);
    } catch (final DataAccessException e) {
      log.error(e);
      throw e;
    }
  }
}
