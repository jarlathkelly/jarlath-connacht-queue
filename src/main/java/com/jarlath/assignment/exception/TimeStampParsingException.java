package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link TimeStampParsingException} extends RuntimeException.
 * This exception will be thrown when a Work Order createdTs
 * encounters an issue during parsing.
 *
 * @author Jarlath Kelly
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "There was an error parsing the Timestamp Parameter")

public class TimeStampParsingException extends RuntimeException {

  public TimeStampParsingException(final String s) {
    super("There was an error parsing the Timestamp Parameter: " + s);
  }
}
