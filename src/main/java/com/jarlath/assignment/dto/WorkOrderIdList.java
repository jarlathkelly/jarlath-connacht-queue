package com.jarlath.assignment.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by jarlath.kelly on 30/03/2016.
 */
public class WorkOrderIdList  extends ResourceSupport {
  private List<Long> workOrderIdList;

  public WorkOrderIdList() {
  }

  @JsonCreator
  public WorkOrderIdList(@JsonProperty("workOrderIdList")List<Long> workOrderIdList) {
    this.workOrderIdList = workOrderIdList;
  }

  public List<Long> getWorkOrderIdList() {
    return workOrderIdList;
  }

  public void setWorkOrderIdList(List<Long> workOrderIdList) {
    this.workOrderIdList = workOrderIdList;
  }
}
