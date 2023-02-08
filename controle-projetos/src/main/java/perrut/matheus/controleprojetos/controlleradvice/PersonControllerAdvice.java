package perrut.matheus.controleprojetos.controlleradvice;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import perrut.matheus.controleprojetos.dto.RequestErrorDTO;
import perrut.matheus.controleprojetos.exception.DuplicatedPersonException;
import perrut.matheus.controleprojetos.exception.PersonIsManagerException;

@ControllerAdvice
public class PersonControllerAdvice {

  @ExceptionHandler(DuplicatedPersonException.class)
  public ResponseEntity<RequestErrorDTO> duplicatedPersonException(
      DuplicatedPersonException e
  ) {
    RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
    requestErrorDTO.setErrorMessage(
        String.format("Person with cpf: %s already exists", e.getPerson().getCpf()));
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(requestErrorDTO);
  }

  @ExceptionHandler(PersonIsManagerException.class)
  public ResponseEntity<RequestErrorDTO> personIsManagerException(
      PersonIsManagerException e
  ) {
    RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
    List<String> projectIds = e.getProjects().stream().map(project -> project.getId().toString())
        .collect(
            Collectors.toList());
    requestErrorDTO.setErrorMessage(
        String.format("Person with id: %s is managing the following projects: %s",
            e.getPerson().getId(), projectIds));
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(requestErrorDTO);
  }
}
