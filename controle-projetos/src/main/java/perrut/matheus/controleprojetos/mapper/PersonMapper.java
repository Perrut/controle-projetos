package perrut.matheus.controleprojetos.mapper;

import org.mapstruct.Mapper;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.dto.PersonDTO;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO personToDTO(Person person);
    Person dtoToPerson(PersonDTO personDTO);
}
