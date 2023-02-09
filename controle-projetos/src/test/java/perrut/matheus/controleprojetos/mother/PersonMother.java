package perrut.matheus.controleprojetos.mother;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

  public static Iterable<Person> getPeopleIterable() {
    List<Person> personList = new ArrayList<>();

    personList.add(getPerson());

    return personList;
  }

  public static List<Person> getManagerList() {
    return Arrays.asList(getNotEmployeePerson());
  }

  public static List<Person> getEmployeeList() {
    return Arrays.asList(getPerson());
  }

  public static List<Person> getSameCpfList() {
    Person person = new Person();

    person.setName("Name");
    person.setEmployee(true);
    person.setBirthDate(LocalDate.of(2022, 3, 5));
    person.setCpf("71772716006");
    person.setId(3l);

    return Arrays.asList(getPerson(), person);
  }
}
