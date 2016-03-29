package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jarlath.kelly on 26/03/2016.
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ID Parameter Value is Invalid")
public class InvalidIdParameterException extends RuntimeException {

  public InvalidIdParameterException() {
  }

  public InvalidIdParameterException(String s) {
    super("ID Parameter Value is Invalid: " + s);
  }
}
