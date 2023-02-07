package perrut.matheus.controleprojetos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import perrut.matheus.controleprojetos.dto.ProjectDTO;
import perrut.matheus.controleprojetos.mapper.ProjectMapper;
import perrut.matheus.controleprojetos.service.ProjectService;
import perrut.matheus.controleprojetos.testejsp.Book;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

  @Autowired
  ProjectMapper projectMapper;

  @Autowired
  ProjectService projectService;

  @GetMapping("/new")
  public String addProject(Model model) {
    model.addAttribute("project", new ProjectDTO());
    return "project/project-form";
  }
}
