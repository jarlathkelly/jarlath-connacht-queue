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

  }

