package com.credibanco.assessment.card.util;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.credibanco.assessment.card.dto.ResponseStatus;
import com.credibanco.assessment.card.dto.ValidationError;
import com.credibanco.assessment.card.enums.ResponseStatusCode;
import com.credibanco.assessment.card.pattern.IRestResponse;
import com.credibanco.assessment.card.pattern.impl.RestResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseEntityUtil {
  public static <T> ResponseEntity<IRestResponse<T>> createResponseEntity(
      final IRestResponse<T> response) {
    return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatusCode())).body(response);
  }

  public static ResponseEntity<IRestResponse<List<ValidationError>>> createResponseValidationError(
      final List<ValidationError> errors) {
    final RestResponse<List<ValidationError>> fullResponse = new RestResponse<>();

    if (errors != null && !errors.isEmpty()) {
      final ResponseStatus responseStatus = getErrorResponseStatus("Validation error");

      fullResponse.setResponse(errors);
      fullResponse.setResponseStatus(responseStatus);
      fullResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
    }

    return createResponseEntity(fullResponse);
  }

  private static ResponseStatus getErrorResponseStatus(final String message) {
    return ResponseStatus.builder()
        .message(ResponseStatusCode.ERROR.getStatus())
        .status(String.valueOf(HttpStatus.BAD_REQUEST))
        .statusCode(ResponseStatusCode.ERROR)
        .detail(message)
        .code(ResponseStatusCode.ERROR.getCode())
        .dateTime(LocalDateTime.now())
        .build();
  }
}
