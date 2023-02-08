package perrut.matheus.controleprojetos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import perrut.matheus.controleprojetos.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  @Query("SELECT p FROM Person p WHERE p.cpf = ?1")
  List<Person> findByCpf(String cpf);

  @Query("SELECT p FROM Person p WHERE p.employee = false")
  List<Person> listEligibleManagers();

  @Query("SELECT p FROM Person p WHERE p.employee = true")
  List<Person> listEmployees();
}
