package perrut.matheus.controleprojetos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import perrut.matheus.controleprojetos.domain.Project;
import perrut.matheus.controleprojetos.dto.ProjectDTO;
import perrut.matheus.controleprojetos.mapper.ProjectMapper;
import perrut.matheus.controleprojetos.service.ProjectService;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

  @Autowired
  ProjectMapper projectMapper;

  @Autowired
  ProjectService projectService;

  //TODO: remove
  @GetMapping("/list-all")
  @ResponseBody
  public List<ProjectDTO> getProjects() {
    return projectMapper.toDtoList(projectService.findAll());
  }

  @GetMapping("/{id}")
  public String viewProject(Model model, @PathVariable Long id) {
    model.addAttribute("project", projectService.findById(id));
    return "project/project-details";
  }

  @GetMapping("/list")
  public String viewProjects(Model model) {
    model.addAttribute("projects", projectService.findAll());
    return "project/project-list";
  }

  @GetMapping("/new")
  public String addProjectView(Model model) {
    model.addAttribute("projectAction", "New project");
    model.addAttribute("project", new ProjectDTO());
    return "project/project-form";
  }

  @PostMapping("/new")
  public RedirectView addProject(@ModelAttribute("project") ProjectDTO projectDTO,
      RedirectAttributes redirectAttributes) {
    final RedirectView redirectView = new RedirectView("/project/new", true);

    ProjectDTO savedProjectDTO = projectMapper
        .projectToDTO(projectService.saveProject(projectMapper.dtoToProject(projectDTO)));

    redirectAttributes.addFlashAttribute("savedProject", savedProjectDTO);
    redirectAttributes.addFlashAttribute("addProjectSuccess", true);
    redirectAttributes.addFlashAttribute("projectAction", "New project");
    return redirectView;
  }

  @GetMapping("/{id}/edit")
  public String editProjectView(Model model, @PathVariable Long id) {
    model.addAttribute("projectAction", "Update project");
    model.addAttribute("project",
        projectMapper.projectToDTO(projectService.findById(id)));
    return "project/project-form";
  }

  @PostMapping("/{id}/edit")
  public RedirectView editProject(
      @ModelAttribute("project") ProjectDTO projectDTO,
      @PathVariable Long id,
      RedirectAttributes redirectAttributes) {
    final RedirectView redirectView = new RedirectView("/project/new", true);

    ProjectDTO updatedProjectDTO = projectMapper
        .projectToDTO(projectService.updateProject(projectMapper.dtoToProject(projectDTO), id));

    redirectAttributes.addFlashAttribute("updatedProject", updatedProjectDTO);
    redirectAttributes.addFlashAttribute("updateProjectSuccess", true);
    redirectAttributes.addFlashAttribute("projects", "Update project");
    return redirectView;
  }

  @PostMapping("/{id}")
  public RedirectView deleteProject(
      @PathVariable Long id,
      RedirectAttributes redirectAttributes) {
    final RedirectView redirectView = new RedirectView("/project/list", true);

    projectService.delete(id);

    redirectAttributes.addFlashAttribute("deletedProjectId", id);
    redirectAttributes.addFlashAttribute("deleteProjectSuccess", true);
    redirectAttributes.addFlashAttribute("projects", projectService.findAll());
    return redirectView;
  }
}
