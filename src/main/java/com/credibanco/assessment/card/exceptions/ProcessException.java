package com.credibanco.assessment.card.exceptions;

/**
 * Excepcion para errores en flujo de controladores
 *
 * @author jsbuitrago
 */
public class ProcessException extends Exception {

  private static final long serialVersionUID = -8821878096577220815L;

  public ProcessException(final String message, final Throwable throwable) {
    super(message, throwable);
  }

  public ProcessException(final String message) {
    super(message);
  }
}
