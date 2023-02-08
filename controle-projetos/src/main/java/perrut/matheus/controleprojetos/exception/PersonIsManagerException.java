package perrut.matheus.controleprojetos.exception;

import java.util.List;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.domain.Project;

public class PersonIsManagerException extends RuntimeException {

  private final Person person;
  private final List<Project> projects;

  public PersonIsManagerException(Person person, List<Project> projects) {
    this.person = person;
    this.projects = projects;
  }

  public Person getPerson() {
    return person;
  }

  public List<Project> getProjects() {
    return projects;
  }
}
