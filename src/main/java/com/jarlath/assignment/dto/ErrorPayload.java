package com.jarlath.assignment.dto;

import java.util.Map;

/**
 * Created by jarlath.kelly on 26/03/2016.
 */
public class ErrorPayload {

    public Integer status;
    public String error;
    public String message;
    public String timeStamp;
    public String trace;

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

  public void setTrace(String trace) {
    this.trace = trace;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }
}

