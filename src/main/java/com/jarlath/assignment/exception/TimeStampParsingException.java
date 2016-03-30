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
    super("The CreatedTs Parameter Value "+ s + " is Invalid. The timestamp must be 14 characters long and conform" +
        "to the following format: ddMMyyyyHHmmss  " +
        "Where dd represents day (valid range 1-31)\n" +
        "MM represents month (valid range 1-12)\n" +
        "yyyy represents year (valid range 2010-2199)\n" +
        "HH represents hour (valid range 00-24)\n" +
        "mm represents year (valid range 00-59)\n" +
        "ss represents year (valid range 00-59)" );
  }
}
