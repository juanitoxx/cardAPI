package com.credibanco.assessment.card.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;

import com.credibanco.assessment.card.api.client.exceptions.InvalidRequestException;
import com.credibanco.assessment.card.dto.ValidationError;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtil {
  private static List<ValidationError> getListBindingResultErrors(
      final BindingResult bindingResult) {
    final List<ValidationError> errors = new ArrayList<>();

    if (bindingResult == null) {
      return errors;
    }

    bindingResult
        .getFieldErrors()
        .forEach(
            error ->
                errors.add(
                    ValidationError.builder()
                        .defaultMessage(error.getDefaultMessage())
                        .code(error.getCode())
                        .field(error.getField())
                        .build()));

    return errors;
  }

  public static void validateBindingResult(final BindingResult result) {
    final List<ValidationError> errors = ValidationUtil.getListBindingResultErrors(result);
    if (!errors.isEmpty()) {
      throw new InvalidRequestException(errors);
    }
  }
}
