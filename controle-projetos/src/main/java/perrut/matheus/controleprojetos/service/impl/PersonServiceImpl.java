package perrut.matheus.controleprojetos.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.exception.DuplicatedPersonException;
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
  public Person savePerson(@Valid Person person) {
    verifyCpfAlreadyExists(person);
    return personRepository.save(person);
  }

  @Override
  public List<Person> findAll() {
    return StreamSupport
        .stream(personRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public Person updatePerson(@Valid Person newPerson, Long id) {
    Person person = personRepository.findById(id).orElseThrow();

    verifyCpfAlreadyExists(person);

    person.setCpf(newPerson.getCpf());
    person.setBirthDate(newPerson.getBirthDate());
    person.setEmployee(newPerson.getEmployee());
    person.setName(newPerson.getName());

    return personRepository.save(person);
  }

  private void verifyCpfAlreadyExists(Person person) {
    List<Person> personList = personRepository.findByCpf(person.getCpf());
    if (!personList.isEmpty()) {
      List<Person> differentIdPersonList = personList.stream().filter(p ->
          p.getId() != person.getId()
      ).collect(Collectors.toList());
      if (!differentIdPersonList.isEmpty()) {
        throw new DuplicatedPersonException(person);
      }
    }
  }
}
