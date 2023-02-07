package perrut.matheus.controleprojetos.enums;

public enum ProjectStatus {
  ANALYZING("EM_ANALISE"),
  PERFORMED_ANALYSIS("ANALISE_REALIZADA"),
  ANALYSIS_APPROVED("ANALISE_APROVADA"),
  STARTED("INICIADO"),
  PLANNED("PLANEJADO"),
  IN_PROGRESS("EM_ANDAMENTO"),
  FINISHED("ENCERRADO"),
  CANCELED("CANCELADO");

  private String status;

  ProjectStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
