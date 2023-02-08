package perrut.matheus.controleprojetos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perrut.matheus.controleprojetos.domain.Member;
import perrut.matheus.controleprojetos.repository.MemberRepository;
import perrut.matheus.controleprojetos.repository.PersonRepository;
import perrut.matheus.controleprojetos.repository.ProjectRepository;
import perrut.matheus.controleprojetos.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private PersonRepository personRepository;

  @Override
  public Member findById(Long id) {
    return memberRepository.findById(id).orElseThrow();
  }

  @Override
  @Transactional
  public Member saveMember(Member member) {
    projectRepository.findById(member.getProjectId()).orElseThrow();
    personRepository.findById(member.getPersonId()).orElseThrow();

    memberRepository.findById(member.getProjectId()).ifPresent(m -> {
      memberRepository.delete(m);
    });

    return memberRepository.save(member);
  }
}
