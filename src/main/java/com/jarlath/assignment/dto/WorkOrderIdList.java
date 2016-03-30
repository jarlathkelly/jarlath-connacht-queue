package com.jarlath.assignment.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jarlath.assignment.dao.WorkOrderQueue;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * The {@link WorkOrderIdList} represents a transfer object for a List of Id's
 *
 * @author Jarlath Kelly
 * @see WorkOrder
 * @see WorkOrderQueue
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
