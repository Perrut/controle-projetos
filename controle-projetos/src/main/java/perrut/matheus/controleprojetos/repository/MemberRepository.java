package perrut.matheus.controleprojetos.repository;

import org.springframework.data.repository.CrudRepository;
import perrut.matheus.controleprojetos.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {

}
