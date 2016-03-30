package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link IdOutOfRangeException} extends RuntimeException.
 * This exception will be thrown when an out of range Work Order Id is encountered.
 *
 * @author Jarlath Kelly
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ID Parameter Value is not within Range")
public class IdOutOfRangeException extends RuntimeException {
  public IdOutOfRangeException(String s) {
    super("WorkOrder ID supplied: " +s +" is not within the valid numeric range of 1-9223372036854775807. ");
  }
}
