package perrut.matheus.controleprojetos.service;

import perrut.matheus.controleprojetos.domain.Member;

public interface MemberService {

  Member findById(Long id);

  Member saveMember(Member member);
}
