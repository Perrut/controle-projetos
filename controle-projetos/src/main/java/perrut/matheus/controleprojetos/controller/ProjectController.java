package perrut.matheus.controleprojetos.controller;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

  private static final String PROJECT_ATTRIBUTE = "project";
  private static final String PROJECT_MEMBER_ATTRIBUTE = "projectMember";
  private static final String PROJECTS_ATTRIBUTE = "projects";
  private static final String PROJECT_ACTION_ATTRIBUTE = "projectAction";
  private static final String ELIGIBLE_MANAGERS_ATTRIBUTE = "eligibleManagers";
  private static final String PROJECT_LIST = "/project/list";

  @GetMapping("/{id}")
  public String viewProject(Model model, @PathVariable Long id) {
    model.addAttribute(PROJECT_ATTRIBUTE, projectService.findById(id));
    PersonDTO projectMember = personMapper.personToDTO(personService.findByProjectId(id));
    if (Objects.nonNull(projectMember)) {
      model.addAttribute(PROJECT_MEMBER_ATTRIBUTE, projectMember);
    }
    return "project/project-details";
  }

  @GetMapping("/list")
  public String viewProjects(Model model) {
    model.addAttribute(PROJECTS_ATTRIBUTE, projectService.findAll());
    return "project/project-list";
  }

  @GetMapping("/new")
  public String addProjectView(Model model) {
    model.addAttribute(PROJECT_ACTION_ATTRIBUTE, "New project");
    model.addAttribute(PROJECT_ATTRIBUTE, new ProjectDTO());
    model.addAttribute("addProject", true);
    model.addAttribute(ELIGIBLE_MANAGERS_ATTRIBUTE,
        personMapper.toDtoList(personService.listEligibleManagers()));
    return "project/project-form";
  }

  @PostMapping("/new")
  public RedirectView addProject(@ModelAttribute(PROJECT_ATTRIBUTE) ProjectDTO projectDTO,
      RedirectAttributes redirectAttributes) {
    final RedirectView redirectView = new RedirectView(PROJECT_LIST, true);

    ProjectDTO savedProjectDTO = projectMapper
        .projectToDTO(projectService.saveProject(projectMapper.dtoToProject(projectDTO)));

    redirectAttributes.addFlashAttribute("savedProject", savedProjectDTO);
    redirectAttributes.addFlashAttribute("addProjectSuccess", true);
    redirectAttributes.addFlashAttribute(PROJECTS_ATTRIBUTE,
        projectMapper.toDtoList(projectService.findAll()));
    return redirectView;
  }

  @GetMapping("/{id}/edit")
  public String editProjectView(Model model, @PathVariable Long id) {
    model.addAttribute(ELIGIBLE_MANAGERS_ATTRIBUTE,
        personMapper.toDtoList(personService.listEligibleManagers()));
    model.addAttribute("employees", personMapper.toDtoList(personService.listEmployees()));
    model.addAttribute(PROJECT_ACTION_ATTRIBUTE, "Update project");
    model.addAttribute("updateProject", true);
    model.addAttribute(PROJECT_ATTRIBUTE,
        projectMapper.projectToDTO(projectService.findById(id)));
    model.addAttribute("member", new MemberDTO());
    PersonDTO projectMember = personMapper.personToDTO(personService.findByProjectId(id));
    if (Objects.nonNull(projectMember)) {
      model.addAttribute(PROJECT_MEMBER_ATTRIBUTE, projectMember);
    }
    return "project/project-form";
  }

  @PostMapping("/{id}/edit")
  public RedirectView editProject(
      @ModelAttribute(PROJECT_ATTRIBUTE) ProjectDTO projectDTO,
      @PathVariable Long id,
      RedirectAttributes redirectAttributes) {
    final RedirectView redirectView = new RedirectView(PROJECT_LIST, true);

    ProjectDTO updatedProjectDTO = projectMapper
        .projectToDTO(projectService.updateProject(projectMapper.dtoToProject(projectDTO), id));

    redirectAttributes.addFlashAttribute("updatedProject", updatedProjectDTO);
    redirectAttributes.addFlashAttribute("updateProjectSuccess", true);
    redirectAttributes.addFlashAttribute(PROJECTS_ATTRIBUTE,
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

    redirectAttributes.addFlashAttribute(ELIGIBLE_MANAGERS_ATTRIBUTE,
        personMapper.toDtoList(personService.listEligibleManagers()));
    redirectAttributes.addFlashAttribute("employees",
        personMapper.toDtoList(personService.listEmployees()));
    redirectAttributes.addFlashAttribute(PROJECT_ACTION_ATTRIBUTE, "Update project");
    redirectAttributes.addFlashAttribute("updateProject", true);
    redirectAttributes.addFlashAttribute(PROJECT_ATTRIBUTE,
        projectMapper.projectToDTO(projectService.findById(id)));
    redirectAttributes.addFlashAttribute("member", new MemberDTO());
    PersonDTO projectMember = personMapper.personToDTO(personService.findByProjectId(id));
    if (Objects.nonNull(projectMember)) {
      redirectAttributes.addFlashAttribute(PROJECT_MEMBER_ATTRIBUTE, projectMember);
    }
    return redirectView;
  }

  @PostMapping("/{id}")
  public RedirectView deleteProject(
      @PathVariable Long id,
      RedirectAttributes redirectAttributes) {
    final RedirectView redirectView = new RedirectView(PROJECT_LIST, true);

    projectService.delete(id);

    redirectAttributes.addFlashAttribute("deletedProjectId", id);
    redirectAttributes.addFlashAttribute("deleteProjectSuccess", true);
    redirectAttributes.addFlashAttribute(PROJECTS_ATTRIBUTE, projectService.findAll());
    return redirectView;
  }
}
