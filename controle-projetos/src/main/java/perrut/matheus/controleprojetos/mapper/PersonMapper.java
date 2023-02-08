package perrut.matheus.controleprojetos.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.dto.PersonDTO;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

  public abstract PersonDTO personToDTO(Person person);

  public abstract Person dtoToPerson(PersonDTO personDTO);

  public List<PersonDTO> toDtoList(List<Person> personList) {
    return personList.stream().map((person) -> this.personToDTO(person))
        .collect(Collectors.toList());
  }
}
