package perrut.matheus.controleprojetos.exception;

import perrut.matheus.controleprojetos.domain.Person;

public class InvalidEmployeeException extends RuntimeException {
  private final Person person;

  public InvalidEmployeeException(Person person) {
    this.person = person;
  }

  public Person getPerson() {
    return person;
  }
}
