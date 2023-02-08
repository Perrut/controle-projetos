package perrut.matheus.controleprojetos.service;

import java.util.List;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.domain.Project;

public interface PersonService {

  Person findById(Long id);

  Person savePerson(Person person);

  List<Person> findAll();

  Person updatePerson(Person newPerson, Long id);

  void delete(Long id);

  List<Person> listEligibleManagers();

  List<Person> listEmployees();

  Person findByProjectId(Long projectId);
}
