package perrut.matheus.controleprojetos.mother;

import java.time.LocalDate;
import perrut.matheus.controleprojetos.domain.Person;

public class PersonMother {
  public static Person getPerson() {
    Person person = new Person();

    person.setName("Name");
    person.setEmployee(true);
    person.setBirthDate(LocalDate.of(2022, 3, 5));
    person.setCpf("99625279024");
    person.setId(1l);

    return person;
  }

  public static Person getNotEmployeePerson() {
    Person person = new Person();

    person.setName("Name2");
    person.setEmployee(false);
    person.setBirthDate(LocalDate.of(2022, 3, 5));
    person.setCpf("11611174074");
    person.setId(2l);

    return person;
  }
}
