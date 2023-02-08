package perrut.matheus.controleprojetos.exception;

import perrut.matheus.controleprojetos.domain.Person;

public class InvalidManagerException extends RuntimeException {

  public final Person person;

  public InvalidManagerException(Person person) {
    this.person = person;
  }

  public Person getPerson() {
    return person;
  }
}
