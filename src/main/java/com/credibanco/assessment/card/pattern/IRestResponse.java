package com.credibanco.assessment.card.pattern;

import com.credibanco.assessment.card.dto.ResponseStatus;

public interface IRestResponse<T> {
  ResponseStatus getResponseStatus();

  T getResponse();

  int getHttpStatusCode();
}
