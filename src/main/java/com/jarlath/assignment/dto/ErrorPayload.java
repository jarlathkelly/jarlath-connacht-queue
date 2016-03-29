package com.jarlath.assignment.dto;

import com.jarlath.assignment.controller.ErrorMessageController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;

import java.util.Map;

/**
 * The {@link ErrorPayload} represents the payload object
 * to be returned when the /error resource is reached.
 *
 * @author Jarlath Kelly
 * @see ErrorMessageController
 * @see ErrorAttributes
 */
public class ErrorPayload {

  public Integer status;
  public String error;
  public String message;
  public String timeStamp;
  public String trace;

  /**
   * Constructor that initialises the members of Payload with
   * those that are supplied by the Error Attributes Object.
   * The method accepts a single parameter; the current time.
   *
   * @param status          Http status
   * @param errorAttributes ErrorAttributes object
   */
  public ErrorPayload(int status, Map<String, Object> errorAttributes) {
    this.status = status;
    this.error = (String) errorAttributes.get("error");
    this.message = (String) errorAttributes.get("message");
    this.timeStamp = errorAttributes.get("timestamp").toString();
    this.trace = (String) errorAttributes.get("trace");
  }

  public String getTrace() {
    return trace;
  }

  public Integer getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public String getMessage() {
    return message;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

}

