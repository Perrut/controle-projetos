package perrut.matheus.controleprojetos.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import perrut.matheus.controleprojetos.domain.Member;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.domain.Project;
import perrut.matheus.controleprojetos.exception.CustomConstraintViolationException;
import perrut.matheus.controleprojetos.exception.IndelibleProjectException;
import perrut.matheus.controleprojetos.exception.InvalidManagerException;
import perrut.matheus.controleprojetos.mother.MemberMother;
import perrut.matheus.controleprojetos.mother.PersonMother;
import perrut.matheus.controleprojetos.mother.ProjectMother;
import perrut.matheus.controleprojetos.repository.MemberRepository;
import perrut.matheus.controleprojetos.repository.PersonRepository;
import perrut.matheus.controleprojetos.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  MemberRepository memberRepository;

  @Mock
  PersonRepository personRepository;

  @InjectMocks
  ProjectServiceImpl projectService;

  Project project;

  Person person;

  Member member;

  @BeforeEach
  void setUp() {
    project = ProjectMother.getProject();
    person = PersonMother.getPerson();
    member = MemberMother.getMember();
  }

  @Test
  void shouldFindById() {
    when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));

    assertNotNull(projectService.findById(project.getId()));
  }

  @Test
  void shouldSaveProject() {
    when(personRepository.findById(project.getManager().getId())).thenReturn(
        Optional.of(project.getManager()));
    when(projectRepository.save(project)).thenReturn(project);

    assertNotNull(projectService.saveProject(project));
  }

  @Test
  void shouldNotSaveProjectWithNoDescription() {
    project.setDescription("");

    assertThrows(CustomConstraintViolationException.class,
        () -> projectService.saveProject(project));
  }

  @Test
  void shouldNotSaveProjectInvalidManager() {
    when(personRepository.findById(project.getManager().getId())).thenReturn(Optional.of(person));

    assertThrows(InvalidManagerException.class, () -> projectService.saveProject(project));
  }

  @Test
  void shouldFindAllProjects() {
    when(projectRepository.findAll()).thenReturn(ProjectMother.getProjectsIterable());

    assertEquals(1, projectService.findAll().size());
  }

  @Test
  void shouldUpdateProject() {
    when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));
    when(personRepository.findById(project.getManager().getId())).thenReturn(
        Optional.of(project.getManager()));
    when(projectRepository.save(project)).thenReturn(project);

    assertNotNull(projectService.updateProject(project, project.getId()));
  }

  @Test
  void shouldDeleteProject() {
    when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));
    when(memberRepository.findById(project.getId())).thenReturn(Optional.of(member));
    doNothing().when(memberRepository).delete(member);
    doNothing().when(projectRepository).delete(project);

    projectService.delete(project.getId());

    verify(projectRepository, times(1)).delete(project);
  }

  @Test
  void shouldNotDeleteIndelibleProject() {
    Project indelibleProject = ProjectMother.getIndelibleProject();
    when(projectRepository.findById(indelibleProject.getId())).thenReturn(
        Optional.of(indelibleProject));

    Long id = indelibleProject.getId();
    assertThrows(IndelibleProjectException.class,
        () -> projectService.delete(id));
  }
}
