package perrut.matheus.controleprojetos.exception;

import perrut.matheus.controleprojetos.domain.Person;

public class DuplicatedPersonException extends RuntimeException {

  private final Person person;

  public DuplicatedPersonException(Person person) {
    this.person = person;
  }

  public Person getPerson() {
    return person;
  }
}
