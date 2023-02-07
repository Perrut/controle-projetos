package perrut.matheus.controleprojetos.service;

import perrut.matheus.controleprojetos.domain.Project;

public interface ProjectService {

  Project findById(Long id);

  Project saveProject(Project project);
}
