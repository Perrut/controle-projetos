package perrut.matheus.controleprojetos.enums;

public enum ProjectRisk {
  LOW("BAIXO_RISCO"),
  MEDIUM("MEDIO_RISCO"),
  HIGH("ALTO_RISCO");

  private String risk;

  ProjectRisk(String risk) {
    this.risk = risk;
  }

  public String getRisk() {
    return risk;
  }

  @Override
  public String toString() {
    return risk;
  }
}
