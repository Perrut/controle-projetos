package perrut.matheus.controleprojetos.dto;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotBlank;

public class PersonDTO {

  private Long id;

  @NotBlank
  private String name;

  private LocalDate birthDate;

  private boolean employee;

  @NotBlank
  private String cpf;

  public PersonDTO() {
    // deserialization
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public boolean getEmployee() {
    return employee;
  }

  public void setEmployee(boolean employee) {
    this.employee = employee;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PersonDTO)) {
      return false;
    }
    PersonDTO personDTO = (PersonDTO) o;
    return getId().equals(personDTO.getId()) && Objects.equals(getName(),
        personDTO.getName()) && Objects.equals(getBirthDate(), personDTO.getBirthDate())
        && Objects.equals(getEmployee(), personDTO.getEmployee())
        && Objects.equals(getCpf(), personDTO.getCpf());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getBirthDate(), getEmployee(), getCpf());
  }
}
