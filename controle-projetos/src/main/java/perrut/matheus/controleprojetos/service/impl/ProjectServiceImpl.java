package perrut.matheus.controleprojetos.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perrut.matheus.controleprojetos.domain.Project;
import perrut.matheus.controleprojetos.enums.ProjectStatus;
import perrut.matheus.controleprojetos.exception.IndelibleProjectException;
import perrut.matheus.controleprojetos.repository.MemberRepository;
import perrut.matheus.controleprojetos.repository.ProjectRepository;
import perrut.matheus.controleprojetos.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private MemberRepository memberRepository;

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
    project.setName(newProject.getName());

    return projectRepository.save(project);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    Project project = projectRepository.findById(id).orElseThrow();
    if (cannotBeDeleted(project)) {
      throw new IndelibleProjectException(project);
    }
    memberRepository.findById(id).ifPresent((member -> memberRepository.delete(member)));
    projectRepository.delete(projectRepository.findById(id).orElseThrow());
  }

  private boolean cannotBeDeleted(Project project) {
    return project.getStatus() == ProjectStatus.STARTED
        || project.getStatus() == ProjectStatus.IN_PROGRESS
        || project.getStatus() == ProjectStatus.FINISHED;
  }
}
