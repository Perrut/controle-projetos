package perrut.matheus.controleprojetos.repository;

import org.springframework.data.repository.CrudRepository;
import perrut.matheus.controleprojetos.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

}
