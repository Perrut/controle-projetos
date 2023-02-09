package perrut.matheus.controleprojetos.dto;

import java.util.Objects;

public class MemberDTO {

  Long projectId;

  Long personId;

  public MemberDTO() {
    // deserialization
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
    if (!(o instanceof MemberDTO)) {
      return false;
    }
    MemberDTO memberDTO = (MemberDTO) o;
    return getProjectId().equals(memberDTO.getProjectId()) && getPersonId().equals(
        memberDTO.getPersonId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getProjectId(), getPersonId());
  }
}
