package perrut.matheus.controleprojetos.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "membros")
public class Member {

  @Id
  @Column(name = "idprojeto")
  Long projectId;

  @NotNull
  @Column(name = "idpessoa")
  Long personId;

  public Member() {
  }

  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Member)) {
      return false;
    }
    Member member = (Member) o;
    return getProjectId().equals(member.getProjectId()) && getPersonId().equals(
        member.getPersonId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getProjectId(), getPersonId());
  }
}
