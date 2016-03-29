package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jarlath.kelly on 27/03/2016.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "WorkOrder ID already on Queue")

public class WorkOrderExistsInQueueException extends RuntimeException {

  public WorkOrderExistsInQueueException() {
  }

  public WorkOrderExistsInQueueException(Long i) {
    super("WorkOrder ID already on Queue: " + i);
  }
}
