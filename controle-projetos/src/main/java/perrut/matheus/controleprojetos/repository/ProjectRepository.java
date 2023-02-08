package perrut.matheus.controleprojetos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import perrut.matheus.controleprojetos.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

  @Query("SELECT p FROM Project p WHERE p.manager.id = ?1")
  List<Project> findByManagerId(Long id);
}
