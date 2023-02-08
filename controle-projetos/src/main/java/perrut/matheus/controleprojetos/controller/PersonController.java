package perrut.matheus.controleprojetos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import perrut.matheus.controleprojetos.dto.PersonDTO;
import perrut.matheus.controleprojetos.mapper.PersonMapper;
import perrut.matheus.controleprojetos.service.PersonService;

@Tag(name = "Person")
@RestController
@RequestMapping(value = "/person")
public class PersonController {

  @Autowired
  PersonMapper personMapper;

  @Autowired
  PersonService personService;

  @Operation(summary = "Create a new person")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Person created",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = PersonDTO.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid data supplied",
          content = @Content)})
  @PostMapping
  public PersonDTO savePerson(@RequestBody PersonDTO personDTO) {
    return personMapper.personToDTO(
        personService.savePerson(personMapper.dtoToPerson(personDTO)));
  }

  @Operation(summary = "Update an existing person")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Person updated",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = PersonDTO.class))})})
  @PutMapping("{id}")
  public PersonDTO updatePerson(@RequestBody PersonDTO personDTO, @PathVariable Long id) {
    return personMapper.personToDTO(
        personService.savePerson(personMapper.dtoToPerson(personDTO)));
  }

  @Operation(summary = "List all people")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List people",
          content = {
              @Content(mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))})
  })
  @GetMapping
  public List<PersonDTO> listPeople() {
    return personMapper.toDtoList(
        personService.findAll());
  }

  @Operation(summary = "Find person by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Person found",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = PersonDTO.class))})
  })
  @GetMapping("{id}")
  public PersonDTO getPerson(@PathVariable Long id) {
    return personMapper.personToDTO(personService.findById(id));
  }

  @Operation(summary = "Delete person by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Person deleted")})
  @DeleteMapping("{id}")
  public void deletePerson(@PathVariable Long id) {
    personService.delete(id);
  }
}
