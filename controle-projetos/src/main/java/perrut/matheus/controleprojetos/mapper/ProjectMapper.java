package perrut.matheus.controleprojetos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import perrut.matheus.controleprojetos.domain.Project;
import perrut.matheus.controleprojetos.dto.ProjectDTO;
import perrut.matheus.controleprojetos.repository.PersonRepository;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

  @Autowired
  protected PersonRepository personRepository;

  @Mapping(target = "managerId", expression = "java(project.getManager().getId())")
  public abstract ProjectDTO projectToDTO(Project project);

  @Mapping(target = "manager", expression = "java(personRepository.findById(projectDTO.getManagerId()).orElse(null))")
  public abstract Project dtoToProject(ProjectDTO projectDTO);
}
