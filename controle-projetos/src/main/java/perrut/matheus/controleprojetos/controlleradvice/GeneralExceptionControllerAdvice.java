package perrut.matheus.controleprojetos.controlleradvice;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GeneralExceptionControllerAdvice {

  @ExceptionHandler(value = BindException.class)
  public ModelAndView customConstraintViolationException(BindException e) {
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("message", "Review the format of data you sent and try again.");
    modelAndView.setViewName("project/project-error");
    return modelAndView;
  }
}
