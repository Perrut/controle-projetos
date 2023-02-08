package perrut.matheus.controleprojetos.mother;

import java.time.LocalDate;
import perrut.matheus.controleprojetos.domain.Project;
import perrut.matheus.controleprojetos.enums.ProjectRisk;
import perrut.matheus.controleprojetos.enums.ProjectStatus;

public class ProjectMother {

  public static Project getProject() {
    Project project = new Project();

    project.setName("Name");
    project.setStatus(ProjectStatus.STARTED);
    project.setRisk(ProjectRisk.LOW);
    project.setDescription("Description");
    project.setEndDate(LocalDate.of(2023, 6, 9));
    project.setStartDate(LocalDate.of(2022, 3, 2));
    project.setExpectedEndDate(LocalDate.of(2023, 5, 4));
    project.setBudget(15l);
    project.setManager(PersonMother.getPerson());
    project.setId(1l);

    return project;
  }
}
