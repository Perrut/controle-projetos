package perrut.matheus.controleprojetos.service;

import perrut.matheus.controleprojetos.domain.Person;

public interface PersonService {

  Person findById(Long id);

  Person savePerson(Person person);
}
