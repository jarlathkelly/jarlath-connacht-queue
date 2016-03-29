package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link InvalidIdParameterException} extends RuntimeException.
 * This exception will be thrown when an Invalid Work Order Id is encountered.
 *
 * @author Jarlath Kelly
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ID Parameter Value is Invalid")
public class InvalidIdParameterException extends RuntimeException {

  public InvalidIdParameterException() {
  }

  public InvalidIdParameterException(String s) {
    super("ID Parameter Value is Invalid: " + s);
  }
}
