package perrut.matheus.controleprojetos.exception;

import perrut.matheus.controleprojetos.domain.Project;

public class IndelibleProjectException extends RuntimeException {
  private final Project project;

  public IndelibleProjectException(Project project) {
    this.project = project;
  }

  public Project getProject() {
    return project;
  }
}
