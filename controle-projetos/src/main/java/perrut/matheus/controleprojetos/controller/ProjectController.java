package perrut.matheus.controleprojetos.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import perrut.matheus.controleprojetos.dto.MemberDTO;
import perrut.matheus.controleprojetos.dto.PersonDTO;
import perrut.matheus.controleprojetos.dto.ProjectDTO;
import perrut.matheus.controleprojetos.mapper.MemberMapper;
import perrut.matheus.controleprojetos.mapper.PersonMapper;
import perrut.matheus.controleprojetos.mapper.ProjectMapper;
import perrut.matheus.controleprojetos.service.MemberService;
import perrut.matheus.controleprojetos.service.PersonService;
import perrut.matheus.controleprojetos.service.ProjectService;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

  @Autowired
  private ProjectMapper projectMapper;

  @Autowired
  private PersonMapper personMapper;

  @Autowired
  private MemberMapper memberMapper;

  @Autowired
  private ProjectService projectService;

  @Autowired
  private PersonService personService;

  @Autowired
  private MemberService memberService;

  @GetMapping("/{id}")
  public String viewProject(Model model, @PathVariable Long id) {
    model.addAttribute("project", projectService.findById(id));
    PersonDTO projectMember = personMapper.personToDTO(personService.findByProjectId(id));
    if (Objects.nonNull(projectMember)) {
      model.addAttribute("projectMember", projectMember);
    }
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
    model.addAttribute("addProject", true);
    return "project/project-form";
  }

  @PostMapping("/new")
  public RedirectView addProject(@ModelAttribute("project") ProjectDTO projectDTO,
      RedirectAttributes redirectAttributes) {
    final RedirectView redirectView = new RedirectView("/project/list", true);

    ProjectDTO savedProjectDTO = projectMapper
        .projectToDTO(projectService.saveProject(projectMapper.dtoToProject(projectDTO)));

    redirectAttributes.addFlashAttribute("savedProject", savedProjectDTO);
    redirectAttributes.addFlashAttribute("addProjectSuccess", true);
    redirectAttributes.addFlashAttribute("projects",
        projectMapper.toDtoList(projectService.findAll()));
    return redirectView;
  }

  @GetMapping("/{id}/edit")
  public String editProjectView(Model model, @PathVariable Long id) {
    model.addAttribute("eligibleManagers",
        personMapper.toDtoList(personService.listEligibleManagers()));
    model.addAttribute("employees", personMapper.toDtoList(personService.listEmployees()));
    model.addAttribute("projectAction", "Update project");
    model.addAttribute("updateProject", true);
    model.addAttribute("project",
        projectMapper.projectToDTO(projectService.findById(id)));
    model.addAttribute("member", new MemberDTO());
    PersonDTO projectMember = personMapper.personToDTO(personService.findByProjectId(id));
    if (Objects.nonNull(projectMember)) {
      model.addAttribute("projectMember", projectMember);
    }
    return "project/project-form";
  }

  @PostMapping("/{id}/edit")
  public RedirectView editProject(
      @ModelAttribute("project") ProjectDTO projectDTO,
      @PathVariable Long id,
      RedirectAttributes redirectAttributes) {
    final RedirectView redirectView = new RedirectView("/project/list", true);

    ProjectDTO updatedProjectDTO = projectMapper
        .projectToDTO(projectService.updateProject(projectMapper.dtoToProject(projectDTO), id));

    redirectAttributes.addFlashAttribute("updatedProject", updatedProjectDTO);
    redirectAttributes.addFlashAttribute("updateProjectSuccess", true);
    redirectAttributes.addFlashAttribute("projects",
        projectMapper.toDtoList(projectService.findAll()));
    return redirectView;
  }

  @PostMapping("/{id}/membership")
  public RedirectView addMembership(
      @ModelAttribute("member") MemberDTO memberDTO,
      @PathVariable Long id,
      RedirectAttributes redirectAttributes) {
    final RedirectView redirectView = new RedirectView("/project/" + id + "/edit", true);

    memberService.saveMember(memberMapper.dtoToMember(memberDTO));

    redirectAttributes.addFlashAttribute("eligibleManagers",
        personMapper.toDtoList(personService.listEligibleManagers()));
    redirectAttributes.addFlashAttribute("employees",
        personMapper.toDtoList(personService.listEmployees()));
    redirectAttributes.addFlashAttribute("projectAction", "Update project");
    redirectAttributes.addFlashAttribute("updateProject", true);
    redirectAttributes.addFlashAttribute("project",
        projectMapper.projectToDTO(projectService.findById(id)));
    redirectAttributes.addFlashAttribute("member", new MemberDTO());
    PersonDTO projectMember = personMapper.personToDTO(personService.findByProjectId(id));
    if (Objects.nonNull(projectMember)) {
      redirectAttributes.addFlashAttribute("projectMember", projectMember);
    }
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
