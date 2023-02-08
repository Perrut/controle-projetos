package perrut.matheus.controleprojetos.service;

import java.util.List;
import perrut.matheus.controleprojetos.domain.Project;

public interface ProjectService {

  Project findById(Long id);

  Project saveProject(Project project);

  List<Project> findAll();

  Project updateProject(Project newProject, Long id);

  void delete(Long id);
}
