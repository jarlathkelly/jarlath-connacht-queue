package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link WorkOrderIdNotOnQueueException} extends RuntimeException.
 * This exception will be thrown when Work Order ID does not exist in the Queue.
 *
 * @author  Jarlath Kelly
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "WorkOrder ID not Found on Queue")
public class WorkOrderIdNotOnQueueException extends RuntimeException {

  public WorkOrderIdNotOnQueueException() {
  }

  public WorkOrderIdNotOnQueueException(Long i) {
    super("WorkOrder ID not Found on Queue: " + i);
  }
}
