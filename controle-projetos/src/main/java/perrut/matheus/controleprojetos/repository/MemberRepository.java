package perrut.matheus.controleprojetos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import perrut.matheus.controleprojetos.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {

  @Query("SELECT m FROM Member m WHERE m.personId = ?1")
  List<Member> findByPersonId(Long id);
}
