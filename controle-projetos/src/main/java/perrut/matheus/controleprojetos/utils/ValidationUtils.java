package perrut.matheus.controleprojetos.utils;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationUtils {

  private ValidationUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static <T> String validate(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<T>> violations = validator.validate(object);

    return violations.
        stream().
        map(violation ->
            String.format("%s %s", violation.getPropertyPath().toString(),
                violation.getMessage())).
        collect(Collectors.joining(","));
  }
}
