package com.jarlath.assignment.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jarlath.assignment.dao.WorkOrderQueue;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;

/**
 * The {@link WorkOrderQueueParam} is a transfer object
 * to store characteristics of the Work Order Queue
 *
 * @author Jarlath Kelly
 * @see WorkOrder
 * @see WorkOrderQueue
 */
public class WorkOrderQueueParam extends ResourceSupport {
  @NotNull
  private Long waitTime;

  public WorkOrderQueueParam() {
  }

  @JsonCreator
  public WorkOrderQueueParam(@JsonProperty("waitTime") Long waitTime) {
    this.waitTime = waitTime;
  }

  public Long getWaitTime() {
    return waitTime;
  }

  public void setWaitTime(final Long value) {
    this.waitTime = value;
  }
}
