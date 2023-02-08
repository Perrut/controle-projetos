package perrut.matheus.controleprojetos.dto;

import java.util.Objects;

public class RequestErrorDTO {
  private String errorMessage;

  public RequestErrorDTO() {
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RequestErrorDTO)) {
      return false;
    }
    RequestErrorDTO that = (RequestErrorDTO) o;
    return getErrorMessage().equals(that.getErrorMessage());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getErrorMessage());
  }
}
