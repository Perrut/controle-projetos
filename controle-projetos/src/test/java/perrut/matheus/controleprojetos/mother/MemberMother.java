package perrut.matheus.controleprojetos.mother;

import java.util.Arrays;
import java.util.List;
import perrut.matheus.controleprojetos.domain.Member;

public class MemberMother {
  public static Member getMember() {
    Member member = new Member();

    member.setPersonId(PersonMother.getPerson().getId());
    member.setProjectId(ProjectMother.getProject().getId());

    return member;
  }

  public static List<Member> getMemberList() {
    return Arrays.asList(getMember());
  }
}
