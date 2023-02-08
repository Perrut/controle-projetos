package perrut.matheus.controleprojetos.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.domain.Project;
import perrut.matheus.controleprojetos.enums.ProjectStatus;
import perrut.matheus.controleprojetos.exception.CustomConstraintViolationException;
import perrut.matheus.controleprojetos.exception.IndelibleProjectException;
import perrut.matheus.controleprojetos.exception.InvalidManagerException;
import perrut.matheus.controleprojetos.repository.MemberRepository;
import perrut.matheus.controleprojetos.repository.PersonRepository;
import perrut.matheus.controleprojetos.repository.ProjectRepository;
import perrut.matheus.controleprojetos.service.ProjectService;
import perrut.matheus.controleprojetos.utils.ValidationUtils;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private ValidatorFactory validatorFactory;

  @Override
  public Project findById(Long id) {
    return projectRepository.findById(id).orElseThrow();
  }

  @Override
  @Transactional
  public Project saveProject(Project project) {
    validateProject(project);
    validateManager(project);
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

    validateProject(project);
    validateManager(project);

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

  private void validateManager(Project project) {
    Person person = personRepository.findById(project.getManager().getId()).orElseThrow();
    if (person.getEmployee()) {
      throw new InvalidManagerException(person);
    }
  }

  // Não estou usando a anotação @Valid nas validações dessa entidade
  // pois o ControllerAdvice de ConstraintViolation fará com que
  // seja exibido um json na tela, causando uma UX ruim
  private void validateProject(Project project) {
    String violations = ValidationUtils.validate(project);
    if (!violations.isEmpty()) {
      throw new CustomConstraintViolationException(violations);
    }
  }
}
