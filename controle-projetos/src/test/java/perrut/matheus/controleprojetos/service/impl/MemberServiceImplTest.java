package perrut.matheus.controleprojetos.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
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
    when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));
    when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
    when(memberRepository.findById(member.getProjectId())).thenReturn(Optional.of(member));
    doNothing().when(memberRepository).delete(any(Member.class));
    when(memberRepository.save(any(Member.class)))
        .thenReturn(MemberMother.getMember());

    Member newMember = memberService.saveMember(member);

    assertEquals(project.getId(), newMember.getProjectId());
  }

  @Test
  void shouldFailOnSaveMemberThatIsNotEmployee() {
    Person notEmployee = PersonMother.getNotEmployeePerson();
    member.setPersonId(notEmployee.getId());
    when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));
    when(personRepository.findById(notEmployee.getId()))
        .thenReturn(Optional.of(notEmployee));

    assertThrows(InvalidEmployeeException.class, () -> {
      memberService.saveMember(member);
    });
  }
}
