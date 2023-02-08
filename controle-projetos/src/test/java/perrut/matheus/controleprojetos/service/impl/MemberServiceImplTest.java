package perrut.matheus.controleprojetos.service.impl;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import perrut.matheus.controleprojetos.domain.Member;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.domain.Project;
import perrut.matheus.controleprojetos.exception.InvalidEmployeeException;
import perrut.matheus.controleprojetos.mother.MemberMother;
import perrut.matheus.controleprojetos.mother.PersonMother;
import perrut.matheus.controleprojetos.mother.ProjectMother;
import perrut.matheus.controleprojetos.repository.MemberRepository;
import perrut.matheus.controleprojetos.repository.PersonRepository;
import perrut.matheus.controleprojetos.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
public class MemberServiceImplTest {

  @Mock
  MemberRepository memberRepository;

  @Mock
  ProjectRepository projectRepository;

  @Mock
  PersonRepository personRepository;

  @InjectMocks
  MemberServiceImpl memberService;

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
  void shouldSaveMemberSuccessfully() {
    Mockito.when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));
    Mockito.when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
    Mockito.when(memberRepository.findById(member.getProjectId())).thenReturn(Optional.of(member));
    Mockito.doNothing().when(memberRepository).delete(Mockito.any(Member.class));
    Mockito.when(memberRepository.save(Mockito.any(Member.class)))
        .thenReturn(MemberMother.getMember());

    Member newMember = memberService.saveMember(member);

    Assertions.assertEquals(project.getId(), newMember.getProjectId());
  }

  @Test
  void shouldFailOnSaveMemberThatIsNotEmployee() {
    Person notEmployee = PersonMother.getNotEmployeePerson();
    member.setPersonId(notEmployee.getId());
    Mockito.when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));
    Mockito.when(personRepository.findById(notEmployee.getId()))
        .thenReturn(Optional.of(notEmployee));

    Assertions.assertThrows(InvalidEmployeeException.class, () -> {
      memberService.saveMember(member);
    });
  }
}
