package com.jarlath.assignment.dto;

import com.jarlath.assignment.exception.InvalidIdParameterException;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import com.jarlath.assignment.service.ValidationServiceImpl;
import com.jarlath.assignment.service.WorkOrderServiceImpl;
import com.jarlath.assignment.util.Statics;
import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Comparator;

/**
 * The {@link WorkOrder} represents the Work Order Request object. The object
 * contains 2 members. The class implements Comparator and Comparable in order to
 * override both the compareTo() and compare() methods. This class is designed to
 * populate a prioirtised queue. In order to be sortable each element of the
 * queue will be comparable to any othe element on the queue.
 * <p>
 * Id is the unique identifer of the Work Order.
 * CreatedTs is the timestamp this Work Order was placed on the queue
 *
 * @author Jarlath Kelly
 * @see com.jarlath.assignment.dao.WorkOrderQueue
 * @see WorkOrderServiceImpl
 */
public class WorkOrder extends ResourceSupport implements Comparator<WorkOrder>, Comparable<WorkOrder> {
  @NotNull
  private String workOrderId;
  @NotNull
  private String createdTs;
  private Integer position = null;

  public WorkOrder() {
  }

  @JsonCreator
  public WorkOrder(@JsonProperty("id") String id, @JsonProperty("createdTS") String createdTS) {
    this.workOrderId = id;
    this.createdTs = createdTS;
  }

  @JsonCreator
  public WorkOrder(@JsonProperty("id") String id, @JsonProperty("createdTS") String createdTS, @JsonProperty("position") Integer position) {
    this.workOrderId = id;
    this.createdTs = createdTS;
    this.position = position;
  }

  public String getWorkOrderId() {
    return workOrderId;
  }

  public String getCreatedTS() {
    return createdTs;
  }

  public Integer getPosition() {
    return position;
  }

  public void setWorkOrderId(final String workOrderId) {
    this.workOrderId = workOrderId;
  }

  public void setPosition(final Integer position) {
    this.position = position;
  }

  /**
   * Overrides the compare() method of the Comparable Interface.
   * This method facilitates the natural ordering by Id of
   * Work Order Objects.
   * <p>
   * This method returns 0 if Work Order Ids are equal.
   * 1 if this Work Order Id is greater than that of the Work Order being compared to
   * -1 if this Work Order Id is less than that of the Work Order being compared to
   *
   * @return int
   */
  @Override
  public int compareTo(WorkOrder anotherWorkOrder) {
    if (anotherWorkOrder == null) {
      throw new ClassCastException("A WorkOrder object expected.");
    }
    String anotherWorkOrderId = anotherWorkOrder.getWorkOrderId();
    return (this.workOrderId).compareTo(anotherWorkOrderId);
  }

  /**
   * Provides basic validation of a WorkOrder
   * The method calls the Validation Service to validate
   * both the WorkOrder Id and createdTs members.
   * Id must be a valid number between 1 and 9223372036854775807
   * createdTs must be a valid Timestamp with the format: DDMMYYYYHHMMSS
   *
   * @param order WorkOrder
   * @return boolean
   */
  public boolean isValid(WorkOrder order) throws InvalidIdParameterException, InvalidTimestampParameterException {
    ValidationServiceImpl validationService = new ValidationServiceImpl();
    return (validationService.isCreatedTsValid(order.getCreatedTS()) && validationService.isIdValid(order.getWorkOrderId()));
  }

  /**
   * Overrides the compare() method of the Comparator Interface.
   * This method facilitates the custome ordering by rank of
   * Work Order Objects.
   * <p>
   * The custom ranking of the Work Order Requests is governed by the following
   * four rules;
   * (1) Normal IDs rank = to the no of seconds on queue.
   * (2) Priority IDs rank = max(3, n log n) where n is no of seconds on queue
   * (3) VIP IDs rank = max(4, 2n log n) where n is no of seconds on queue
   * (4) Management Override IDs are ranked ahead other ID
   * and are ranked among themselves according to the no of seconds on queue
   * <p>
   * This method returns 0 if Work Order Ids are equal.
   * 1 if this Work Order Id is greater than that of the Work Order being compared to
   * -1 if this Work Order Id is less than that of the Work Order being compared to
   *
   * @return int
   */
  public int compare(WorkOrder workOrder1, WorkOrder workOrder2) {
    WorkOrderServiceImpl workOrderService = new WorkOrderServiceImpl();

    if (workOrder1 == null || workOrder2 == null) {
      throw new ClassCastException("WorkOrder objects are expected.");
    }

    String workOrderType1 = workOrderService.getWorkOrderType(workOrder1.getWorkOrderId());
    String workOrderType2 = workOrderService.getWorkOrderType(workOrder2.getWorkOrderId());

    if (workOrderType1.equals(Statics.MGMT_OVERRIDE) && !workOrderType2.equals(Statics.MGMT_OVERRIDE)) {
      return -1;
    } else if (workOrderType2.equals(Statics.MGMT_OVERRIDE) && !workOrderType1.equals(Statics.MGMT_OVERRIDE)) {
      return 1;
    } else if (workOrderType1.equals(Statics.MGMT_OVERRIDE) && workOrderType2.equals(Statics.MGMT_OVERRIDE)) {
      Long comparison = workOrderService.getMgmtOverrideRank((workOrder1).getCreatedTS()) - workOrderService.getMgmtOverrideRank((workOrder2).getCreatedTS());
      return workOrderService.getComparisonReturnValue(comparison);
    }

    Long rank1 = workOrderService.getWorkOrderRank(workOrderType1, workOrder1);
    Long rank2 = workOrderService.getWorkOrderRank(workOrderType2, workOrder2);
    Long compare = rank1 - rank2;
    return workOrderService.getComparisonReturnValue(compare);
  }

  /**
   * Override the equals() method in order to allow the ArratList contains
   * method to identify WorkOrder objects based on their Id. This
   * enables the more efficient processing of the List via Random
   * Access methods
   *
   * @param obj WorkOrder
   * @return boolean
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!WorkOrder.class.isAssignableFrom(obj.getClass())) {
      return false;
    }
    final WorkOrder other = (WorkOrder) obj;
    if ((this.workOrderId == null) ? (other.workOrderId != null) : !this.workOrderId.equals(other.workOrderId)) {
      return false;
    }
    return true;
  }

}
