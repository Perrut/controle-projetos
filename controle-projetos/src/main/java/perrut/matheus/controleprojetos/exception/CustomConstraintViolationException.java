package perrut.matheus.controleprojetos.exception;

public class CustomConstraintViolationException extends RuntimeException {

  private final String violations;

  public CustomConstraintViolationException(String violations) {
    this.violations = violations;
  }

  public String getViolations() {
    return violations;
  }
}

