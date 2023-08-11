package com.credibanco.assessment.card.pattern.impl;

import com.credibanco.assessment.card.dto.ResponseStatus;
import com.credibanco.assessment.card.pattern.IRestResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;

@Setter
public class RestResponse<T> implements IRestResponse<T> {
  private T response;
  private ResponseStatus responseStatus;

  @JsonIgnore private int httpStatusCode;

  @Override
  public ResponseStatus getResponseStatus() {
    return this.responseStatus;
  }

  @Override
  public T getResponse() {
    return this.response;
  }

  @Override
  public int getHttpStatusCode() {
    return this.httpStatusCode;
  }
}
