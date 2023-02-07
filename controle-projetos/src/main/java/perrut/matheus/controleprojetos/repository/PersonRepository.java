package perrut.matheus.controleprojetos.repository;

import org.springframework.data.repository.CrudRepository;
import perrut.matheus.controleprojetos.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
