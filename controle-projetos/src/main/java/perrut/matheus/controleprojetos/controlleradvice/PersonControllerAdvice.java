package perrut.matheus.controleprojetos.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import perrut.matheus.controleprojetos.dto.RequestErrorDTO;
import perrut.matheus.controleprojetos.exception.DuplicatedPersonException;

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
}
