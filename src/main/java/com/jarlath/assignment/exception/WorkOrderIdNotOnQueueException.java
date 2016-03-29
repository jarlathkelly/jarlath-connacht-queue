package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jarlath.kelly on 27/03/2016.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "WorkOrder ID not Found on Queue")
public class WorkOrderIdNotOnQueueException extends RuntimeException {

  public WorkOrderIdNotOnQueueException() {
  }

  public WorkOrderIdNotOnQueueException(Long i) {
    super("WorkOrder ID not Found on Queue: " + i);
  }
}
