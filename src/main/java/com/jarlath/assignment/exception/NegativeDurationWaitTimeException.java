package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link NegativeDurationWaitTimeException} extends RuntimeException.
 * This exception will be thrown when a negative wait time is calculated based on the
 * supplied createdTs.
 *
 * @author Jarlath Kelly
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Waittime Calculation reulted in Negative Duration")
public class NegativeDurationWaitTimeException extends RuntimeException {

  public NegativeDurationWaitTimeException() {
    super("Waittime Calculation reulted in Negative Duration. Please ensure the createdTs parameter supplied is" +
        " of the current time and not a stale date");
  }
}
