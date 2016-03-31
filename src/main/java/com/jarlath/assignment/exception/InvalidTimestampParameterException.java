package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link InvalidTimestampParameterException} extends RuntimeException.
 * This exception will be thrown when an Invalid Work Order createdTs is encountered.
 *
 * @author Jarlath Kelly
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "CreatedTs Parameter Value is Invalid")
public class InvalidTimestampParameterException extends RuntimeException {

  public InvalidTimestampParameterException() {
  }

  public InvalidTimestampParameterException(final String s) {
    super("The CreatedTs Parameter Value " + s + " is Invalid. The timestamp must be 14 characters long and conform" +
        "to the following format: ddMMyyyyHHmmss  " +
        "Where dd represents day (valid range 1-31)\n" +
        "MM represents month (valid range 1-12)\n" +
        "yyyy represents year (valid range 2010-2199)\n" +
        "HH represents hour (valid range 00-24)\n" +
        "mm represents year (valid range 00-59)\n" +
        "ss represents year (valid range 00-59)");
  }
}