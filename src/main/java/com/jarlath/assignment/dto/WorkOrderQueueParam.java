package com.jarlath.assignment.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by jarlath.kelly on 30/03/2016.
 */
public class WorkOrderQueueParam   extends ResourceSupport {
  private Long waitTime;

  public WorkOrderQueueParam() {
  }

  @JsonCreator
  public WorkOrderQueueParam(@JsonProperty("waitTime")Long waitTime) {
    this.waitTime = waitTime;
  }

  public Long getWaitTime() {
    return waitTime;
  }

  public void setWaitTime(Long value) {
    this.waitTime = value;
  }
}
