package com.credibanco.assessment.card.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.credibanco.assessment.card.api.client.exceptions.InvalidRequestException;
import com.credibanco.assessment.card.dto.ResponseStatus;
import com.credibanco.assessment.card.dto.ValidationError;
import com.credibanco.assessment.card.enums.ResponseStatusCode;
import com.credibanco.assessment.card.pattern.IRestResponse;
import com.credibanco.assessment.card.util.ResponseEntityUtil;

@RestController
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String ERROR_INTERNO_DEL_SERVIDOR =
      "Error interno del servidor. Intente de nuevo mÃƒÂ¡s tarde.";
  private static final String REQUEST_NO_EXISTE =
      "El recurso del request no se ha podido encontrar pero podrÃƒÂ­a estar disponible en el futuro.";
  private static final String SERVIDOR_NO_PUEDE_PROCESAR_REQUEST =
      "El servidor no puede o no va a procesar el request por un error de sintaxis del cliente.";

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<IRestResponse<List<ValidationError>>> invalidRegistrant(
      final InvalidRequestException ex) {
    final List<ValidationError> errors = ex.getErrors();
    return ResponseEntityUtil.createResponseValidationError(errors);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ResponseStatus> handleException(final Exception ex) {
    return new ResponseEntity<>(
        ResponseStatus.builder()
            .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
            .message(ERROR_INTERNO_DEL_SERVIDOR)
            .detail(ex.getMessage())
            .dateTime(LocalDateTime.now())
            .statusCode(ResponseStatusCode.ERROR)
            .build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    return new ResponseEntity<>(
        ResponseStatus.builder()
            .status(String.valueOf(HttpStatus.BAD_REQUEST.toString()))
            .message(SERVIDOR_NO_PUEDE_PROCESAR_REQUEST)
            .detail(ex.getMessage())
            .dateTime(LocalDateTime.now())
            .statusCode(ResponseStatusCode.ERROR)
            .build(),
        HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
      final NoHandlerFoundException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    return new ResponseEntity<>(
        ResponseStatus.builder()
            .status(String.valueOf(HttpStatus.NOT_FOUND))
            .message(REQUEST_NO_EXISTE)
            .detail(ex.getMessage())
            .dateTime(LocalDateTime.now())
            .statusCode(ResponseStatusCode.ERROR)
            .build(),
        HttpStatus.NOT_FOUND);
  }
}
