package perrut.matheus.controleprojetos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import perrut.matheus.controleprojetos.dto.ProjectDTO;
import perrut.matheus.controleprojetos.mapper.ProjectMapper;
import perrut.matheus.controleprojetos.service.ProjectService;

@Tag(name = "Project")
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

  @Autowired
  ProjectMapper projectMapper;

  @Autowired
  ProjectService projectService;

  @Operation(summary = "Create a new project")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Project created",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ProjectDTO.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied",
          content = @Content)})
  @PostMapping
  public ProjectDTO saveProject(@RequestBody ProjectDTO projectDTO) {
    return projectMapper.projectToDTO(
        projectService.saveProject(projectMapper.dtoToProject(projectDTO)));
  }
}
