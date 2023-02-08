package perrut.matheus.controleprojetos.mother;

import perrut.matheus.controleprojetos.domain.Member;

public class MemberMother {
  public static Member getMember() {
    Member member = new Member();

    member.setPersonId(PersonMother.getPerson().getId());
    member.setProjectId(ProjectMother.getProject().getId());

    return member;
  }
}
