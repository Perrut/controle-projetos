package perrut.matheus.controleprojetos.controlleradvice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import perrut.matheus.controleprojetos.dto.RequestErrorDTO;

@ControllerAdvice
public class ConstraintViolationControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<RequestErrorDTO>
  constraintViolationException(ConstraintViolationException e) {
    RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
    requestErrorDTO.setErrorMessage(
        String.join(",", buildValidationErrors(e.getConstraintViolations())));
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(requestErrorDTO);
  }

  private List<String> buildValidationErrors(
      Set<ConstraintViolation<?>> violations) {
    return violations.
        stream().
        map(violation ->
            String.format("%s %s", violation.getPropertyPath().toString(),
                violation.getMessage())).
        collect(Collectors.toList());
  }
}
