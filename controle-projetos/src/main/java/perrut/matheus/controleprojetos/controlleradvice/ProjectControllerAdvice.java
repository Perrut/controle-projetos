package perrut.matheus.controleprojetos.controlleradvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import perrut.matheus.controleprojetos.exception.IndelibleProjectException;

@ControllerAdvice
public class ProjectControllerAdvice {

  @ExceptionHandler(value = IndelibleProjectException.class)
  public ModelAndView indelibleProjectException(IndelibleProjectException e) {
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("message", String.format(
        "Project %s cannot be deleted, because its status is %s", e.getProject().getId(),
        e.getProject().getStatus()));
    modelAndView.setViewName("project/project-error");
    return modelAndView;
  }
}
