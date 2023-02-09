package perrut.matheus.controleprojetos.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import perrut.matheus.controleprojetos.enums.ProjectRisk;
import perrut.matheus.controleprojetos.enums.ProjectStatus;

@Entity
@Table(name = "projeto")
public class Project implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome")
  @NotBlank
  private String name;

  @Column(name = "data_inicio")
  @NotNull
  private LocalDate startDate;

  @Column(name = "data_previsao_fim")
  @NotNull
  private LocalDate expectedEndDate;

  @Column(name = "data_fim")
  @NotNull
  private LocalDate endDate;

  @Column(name = "descricao")
  @NotBlank
  private String description;

  @Column(name = "orcamento")
  @Min(value = 0)
  private double budget;

  @Column(name = "risco")
  @NotNull
  private ProjectRisk risk;

  @OneToOne
  @JoinColumn(name = "idgerente", referencedColumnName = "id")
  @NotNull
  private Person manager;

  @NotNull
  private ProjectStatus status;

  public Project() {
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

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endData) {
    this.endDate = endData;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }

  public ProjectRisk getRisk() {
    return risk;
  }

  public void setRisk(ProjectRisk risk) {
    this.risk = risk;
  }

  public Person getManager() {
    return manager;
  }

  public void setManager(Person manager) {
    this.manager = manager;
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
    if (!(o instanceof Project)) {
      return false;
    }
    Project project = (Project) o;
    return Double.compare(project.getBudget(), getBudget()) == 0 && getId().equals(
        project.getId()) && Objects.equals(getName(), project.getName())
        && Objects.equals(getStartDate(), project.getStartDate())
        && Objects.equals(getExpectedEndDate(), project.getExpectedEndDate())
        && Objects.equals(getEndDate(), project.getEndDate()) && Objects.equals(
        getDescription(), project.getDescription()) && getRisk() == project.getRisk()
        && Objects.equals(getManager(), project.getManager())
        && getStatus() == project.getStatus();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getStartDate(), getExpectedEndDate(), getEndDate(),
        getDescription(), getBudget(), getRisk(), getManager(), getStatus());
  }
}
