package com.jarlath.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link WorkOrderExistsInQueueException} extends RuntimeException.
 * This exception will be thrown when Work Order ID already exists in the Queue.
 *
 * @author Jarlath Kelly
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "WorkOrder ID already on Queue")

public class WorkOrderExistsInQueueException extends RuntimeException {

  public WorkOrderExistsInQueueException(final String i) {
    super("WorkOrder ID already on Queue: " + i);
  }
}
