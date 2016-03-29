package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link InvalidTimestampParameterException} extends RuntimeException.
 * This exception will be thrown when an Invalid Work Order createdTs is encountered.
 *
 * @author  Jarlath Kelly
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "CreatedTs Parameter Value is Invalid")
public class InvalidTimestampParameterException extends RuntimeException {

  public InvalidTimestampParameterException() {
  }

  public InvalidTimestampParameterException(String s) {
    super("CreatedTs Parameter Value is Invalid: " + s);
  }
}