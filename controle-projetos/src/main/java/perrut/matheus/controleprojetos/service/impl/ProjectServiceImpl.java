package perrut.matheus.controleprojetos.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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

  @Override
  public List<Project> findAll() {
    return StreamSupport
        .stream(projectRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public Project updateProject(Project newProject, Long id) {
    Project project = projectRepository.findById(id).orElseThrow();

    project.setManager(newProject.getManager());
    project.setBudget(newProject.getBudget());
    project.setDescription(newProject.getDescription());
    project.setStartDate(newProject.getStartDate());
    project.setExpectedEndDate(newProject.getExpectedEndDate());
    project.setEndDate(newProject.getEndDate());
    project.setRisk(newProject.getRisk());
    project.setStatus(newProject.getStatus());

    return projectRepository.save(project);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    projectRepository.delete(projectRepository.findById(id).orElseThrow());
  }
}
