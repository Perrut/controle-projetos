package perrut.matheus.controleprojetos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @PostMapping
    public PersonDTO savePerson(@RequestBody PersonDTO personDTO) {
        personDTO.getCpf();
        return personMapper.personToDTO(
                personService.savePerson(personMapper.dtoToPerson(personDTO)));
    }
}
