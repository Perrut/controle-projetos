package perrut.matheus.controleprojetos.dto;

import java.time.LocalDate;
import java.util.Objects;
import perrut.matheus.controleprojetos.enums.ProjectRisk;
import perrut.matheus.controleprojetos.enums.ProjectStatus;

public class ProjectDTO {

  private Long id;

  private String name;

  private LocalDate startDate;

  private LocalDate expectedEndDate;

  private LocalDate endData;

  private String description;

  private float budget;

  private ProjectRisk risk;

  private Long managerId;

  private ProjectStatus status;

  public ProjectDTO() {
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

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getExpectedEndDate() {
    return expectedEndDate;
  }

  public void setExpectedEndDate(LocalDate expectedEndDate) {
    this.expectedEndDate = expectedEndDate;
  }

  public LocalDate getEndData() {
    return endData;
  }

  public void setEndData(LocalDate endData) {
    this.endData = endData;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getBudget() {
    return budget;
  }

  public void setBudget(float budget) {
    this.budget = budget;
  }

  public ProjectRisk getRisk() {
    return risk;
  }

  public void setRisk(ProjectRisk risk) {
    this.risk = risk;
  }

  public Long getManagerId() {
    return managerId;
  }

  public void setManagerId(Long managerId) {
    this.managerId = managerId;
  }

  public ProjectStatus getStatus() {
    return status;
  }

  public void setStatus(ProjectStatus status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProjectDTO)) {
      return false;
    }
    ProjectDTO that = (ProjectDTO) o;
    return Float.compare(that.getBudget(), getBudget()) == 0 && getId().equals(that.getId())
        && Objects.equals(getName(), that.getName()) && Objects.equals(
        getStartDate(), that.getStartDate()) && Objects.equals(getExpectedEndDate(),
        that.getExpectedEndDate()) && Objects.equals(getEndData(), that.getEndData())
        && Objects.equals(getDescription(), that.getDescription())
        && getRisk() == that.getRisk() && Objects.equals(getManagerId(),
        that.getManagerId()) && getStatus() == that.getStatus();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getStartDate(), getExpectedEndDate(), getEndData(),
        getDescription(), getBudget(), getRisk(), getManagerId(), getStatus());
  }
}
