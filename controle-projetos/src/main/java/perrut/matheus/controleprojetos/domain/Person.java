package perrut.matheus.controleprojetos.domain;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "pessoa")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome")
  @NotBlank(message = "can't be blank")
  private String name;

  @Column(name = "datanascimento")
  private LocalDate birthDate;

  @Column(name = "funcionario")
  private boolean employee;

  @Column(unique = true)
  @CPF(message = "invalid")
  private String cpf;

  public Person() {
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
    if (!(o instanceof Person)) {
      return false;
    }
    Person person = (Person) o;
    return getId().equals(person.getId()) && Objects.equals(getName(), person.getName())
        && Objects.equals(getBirthDate(), person.getBirthDate())
        && Objects.equals(getEmployee(), person.getEmployee()) && Objects.equals(
        getCpf(), person.getCpf());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getBirthDate(), getEmployee(), getCpf());
  }
}
