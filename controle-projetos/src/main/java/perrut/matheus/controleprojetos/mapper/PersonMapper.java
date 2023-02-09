package perrut.matheus.controleprojetos.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import perrut.matheus.controleprojetos.domain.Person;
import perrut.matheus.controleprojetos.dto.PersonDTO;

@Mapper(componentModel = "spring")
public interface PersonMapper {

  PersonDTO personToDTO(Person person);

  Person dtoToPerson(PersonDTO personDTO);

  default List<PersonDTO> toDtoList(List<Person> personList) {
    return personList.stream().map(this::personToDTO)
        .collect(Collectors.toList());
  }
}
