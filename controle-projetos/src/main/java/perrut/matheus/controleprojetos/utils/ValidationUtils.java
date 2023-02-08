package perrut.matheus.controleprojetos.utils;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationUtils {

  public static <T> String validate(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<T>> violations = validator.validate(object);

    return String.join(",", violations.
        stream().
        map(violation ->
            String.format("%s %s", violation.getPropertyPath().toString(),
                violation.getMessage())).
        collect(Collectors.toList()));
  }
}
