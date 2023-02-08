package perrut.matheus.controleprojetos.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.domain.Project;
import perrut.matheus.controleprojetos.dto.ProjectDTO;
import perrut.matheus.controleprojetos.service.PersonService;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

  @Autowired
  protected PersonService personService;

  @Mapping(target = "managerId", expression = "java(project.getManager().getId())")
  public abstract ProjectDTO projectToDTO(Project project);

  @Mapping(target = "manager", expression = "java(findById(projectDTO.getManagerId()))")
  public abstract Project dtoToProject(ProjectDTO projectDTO);

  public List<ProjectDTO> toDtoList(List<Project> projects) {
    return projects.stream().map((project) -> this.projectToDTO(project))
        .collect(Collectors.toList());
  }

  protected Person findById(Long id) {
    if (Objects.isNull(id)) {
      return null;
    }
    return personService.findById(id);
  }
}
