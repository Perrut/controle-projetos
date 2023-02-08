package perrut.matheus.controleprojetos.controlleradvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import perrut.matheus.controleprojetos.exception.CustomConstraintViolationException;

@ControllerAdvice
public class CustomConstraintViolatorControllerAdvice {

  @ExceptionHandler(value = CustomConstraintViolationException.class)
  public ModelAndView customConstraintViolationException(CustomConstraintViolationException e) {
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("message", e.getViolations());
    modelAndView.setViewName("project/project-error");
    return modelAndView;
  }
}
