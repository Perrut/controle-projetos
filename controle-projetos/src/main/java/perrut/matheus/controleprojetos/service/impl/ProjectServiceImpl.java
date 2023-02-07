package perrut.matheus.controleprojetos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perrut.matheus.controleprojetos.domain.Project;
import perrut.matheus.controleprojetos.repository.ProjectRepository;
import perrut.matheus.controleprojetos.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Override
  public Project findById(Long id) {
    return projectRepository.findById(id).orElseThrow();
  }

  @Override
  @Transactional
  public Project saveProject(Project project) {
    return projectRepository.save(project);
  }
}
