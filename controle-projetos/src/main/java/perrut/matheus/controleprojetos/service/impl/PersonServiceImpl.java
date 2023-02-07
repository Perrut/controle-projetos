package perrut.matheus.controleprojetos.service.impl;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.repository.PersonRepository;
import perrut.matheus.controleprojetos.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  PersonRepository personRepository;

  @Override
  public Person findById(Long id) {
    return personRepository.findById(id).orElseThrow();
  }

  @Override
  @Transactional
  public Person savePerson(Person person) {
    return personRepository.save(person);
  }
}
