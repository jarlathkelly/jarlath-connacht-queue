package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jarlath.kelly on 28/03/2016.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "There was an error parsing the Timestamp Parameter")

public class TimeStampParsingException extends RuntimeException{
  public TimeStampParsingException() {
  }

  public TimeStampParsingException(String s) {
    super("There was an error parsing the Timestamp Parameter: " + s);
  }
}
