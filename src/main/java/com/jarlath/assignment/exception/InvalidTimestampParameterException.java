package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jarlath.kelly on 26/03/2016.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "CreatedTs Parameter Value is Invalid")
public class InvalidTimestampParameterException extends RuntimeException {

  public InvalidTimestampParameterException() {
  }

  public InvalidTimestampParameterException(String s) {
    super("CreatedTs Parameter Value is Invalid: " + s);
  }
}