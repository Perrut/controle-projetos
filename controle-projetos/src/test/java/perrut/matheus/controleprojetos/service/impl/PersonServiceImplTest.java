package perrut.matheus.controleprojetos.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import perrut.matheus.controleprojetos.exception.DuplicatedPersonException;
import perrut.matheus.controleprojetos.exception.PersonIsManagerException;
import perrut.matheus.controleprojetos.mother.MemberMother;
import perrut.matheus.controleprojetos.mother.PersonMother;
import perrut.matheus.controleprojetos.mother.ProjectMother;
import perrut.matheus.controleprojetos.repository.MemberRepository;
import perrut.matheus.controleprojetos.repository.PersonRepository;
import perrut.matheus.controleprojetos.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

  @Mock
  PersonRepository personRepository;

  @Mock
  MemberRepository memberRepository;

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  PersonServiceImpl personService;

  Person person;

  Project project;

  Member member;

  @BeforeEach
  void setUp() {
    person = PersonMother.getPerson();
    project = ProjectMother.getProject();
    member = MemberMother.getMember();
  }

  @Test
  void shouldFindPersonById() {
    when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));

    Long id = person.getId();
    Person p = personService.findById(person.getId());

    assertEquals(id, p.getId());
  }

  @Test
  void shouldSavePerson() {
    when(personRepository.findByCpf(person.getCpf())).thenReturn(new ArrayList<>());
    when(personRepository.save(person)).thenReturn(person);

    Person p = personService.savePerson(person);

    assertNotNull(p);
  }

  @Test
  void shouldNotSavePersonWithRepeatedCpf() {
    when(personRepository.findByCpf(person.getCpf())).thenReturn(PersonMother.getSameCpfList());

    assertThrows(DuplicatedPersonException.class, () -> personService.savePerson(person));
  }

  @Test
  void shouldFindAll() {
    when(personRepository.findAll()).thenReturn(PersonMother.getPeopleIterable());

    assertEquals(1, personService.findAll().size());
  }

  @Test
  void shouldUpdatePerson() {
    when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
    when(personRepository.findByCpf(person.getCpf())).thenReturn(new ArrayList<>());
    when(personRepository.save(person)).thenReturn(person);

    Person p = personService.updatePerson(person, person.getId());

    assertNotNull(p);
  }

  @Test
  void shouldDeletePerson() {
    when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
    when(memberRepository.findByPersonId(person.getId()))
        .thenReturn(MemberMother.getMemberList());
    doNothing().when(memberRepository).delete(any(Member.class));
    doNothing().when(personRepository).delete(any(Person.class));

    personService.delete(person.getId());

    verify(personRepository, times(1)).delete(any(Person.class));
  }

  @Test
  void shouldFailOnDeletePersonThatIsProjectManager() {
    Person manager = PersonMother.getNotEmployeePerson();
    when(personRepository.findById(manager.getId())).thenReturn(Optional.of(manager));
    when(projectRepository.findByManagerId(manager.getId())).thenReturn(
        ProjectMother.getProjectList());

    Long id = manager.getId();
    assertThrows(PersonIsManagerException.class, () -> personService.delete(id));
  }

  @Test
  void shouldListEligibleManagers() {
    when(personRepository.listEligibleManagers()).thenReturn(PersonMother.getManagerList());

    assertNotNull(personService.listEligibleManagers());
  }

  @Test
  void shouldListEmployees() {
    when(personRepository.listEmployees()).thenReturn(PersonMother.getEmployeeList());

    assertNotNull(personService.listEmployees());
  }

  @Test
  void shouldFindByProjectId() {
    when(memberRepository.findById(project.getId())).thenReturn(
        Optional.of(member));
    when(personRepository.findById(member.getPersonId())).thenReturn(Optional.of(person));

    assertNotNull(personService.findByProjectId(project.getId()));
  }

  @Test
  void shouldFindNoPersonByProjectId() {
    when(memberRepository.findById(project.getId())).thenReturn(
        Optional.empty());

    assertNull(personService.findByProjectId(project.getId()));
  }
}
