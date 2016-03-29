package com.jarlath.assignment.dto;

import com.jarlath.assignment.exception.InvalidIdParameterException;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import com.jarlath.assignment.service.ValidationServiceImpl;
import com.jarlath.assignment.service.WorkOrderServiceImpl;
import com.jarlath.assignment.util.Statics;
import java.text.ParseException;
import java.util.Comparator;


public class WorkOrder implements Comparator<WorkOrder>, Comparable<WorkOrder> {
  private Long id;
  private String createdTs;

  public WorkOrder() {
  }


  public WorkOrder(Long id, String createdTS) {
    this.id = id;
    this.createdTs = createdTS;
  }

  public Long getId() {
    return id;
  }

  public String getCreatedTS() {
    return createdTs;
  }

  @Override
  public int compareTo(WorkOrder anotherWorkOrder) {
    if (!(anotherWorkOrder instanceof WorkOrder))
      throw new ClassCastException("A WorkOrder object expected.");
    Long anotherWorkOrderId = ((WorkOrder) anotherWorkOrder).getId();
    return (this.id).compareTo(anotherWorkOrderId);
  }


  public boolean isValid(WorkOrder order) throws InvalidIdParameterException, InvalidTimestampParameterException {
    ValidationServiceImpl validationService = new ValidationServiceImpl();
    return (validationService.isCreatedTsValid(order.getCreatedTS()) && validationService.isIdValid(order.getId()));
  }

  public int compare(WorkOrder workOrder1, WorkOrder workOrder2) {
    WorkOrderServiceImpl workOrderService = new WorkOrderServiceImpl();
    try {
      if (!(workOrder1 instanceof WorkOrder) || !(workOrder2 instanceof WorkOrder)) {
        throw new ClassCastException("WorkOrder objects are expected.");
      }
      WorkOrder firstWorkOrder = workOrder1;
      WorkOrder secondWorkOrder = workOrder2;
      String workOrderType1 = workOrderService.getWorkOrderType(firstWorkOrder.getId());
      String workOrderType2 = workOrderService.getWorkOrderType(secondWorkOrder.getId());


      if (workOrderType1.equals(Statics.MGMT_OVERRIDE) && !workOrderType2.equals(Statics.MGMT_OVERRIDE)) {
        return -1;
      } else if (workOrderType2.equals(Statics.MGMT_OVERRIDE) && !workOrderType1.equals(Statics.MGMT_OVERRIDE)) {
        return 1;
      } else if (workOrderType1.equals(Statics.MGMT_OVERRIDE) && workOrderType2.equals(Statics.MGMT_OVERRIDE)) {
        Long comparison = workOrderService.getMgmtOverrideRank((workOrder1).getCreatedTS()) - workOrderService.getMgmtOverrideRank((workOrder2).getCreatedTS());
        return workOrderService.getComparisonReturnValue(comparison);
      }

      Long rank1 = workOrderService.getWorkOrderRank(workOrderType1, firstWorkOrder);
      Long rank2 = workOrderService.getWorkOrderRank(workOrderType2, secondWorkOrder);
      Long compare = rank1 - rank2;
      return workOrderService.getComparisonReturnValue(compare);
    } catch (ParseException pe) {
      pe.printStackTrace();
    }
    return 0;

  }

//  protected static int getComparisonReturnValue(Long value) {
//    if (value > 0) {
//      return -1;
//    } else if (value < 0) {
//      return 1;
//    } else {
//      return 0;
//    }
//  }
//
//  protected static Long getWorkOrderRank(String workOrderType, WorkOrder workOrder) throws ParseException {
//    Long rank = 0L;
//
//    if (workOrderType.equals(Statics.VIP)) {
//      return getVipRank(workOrder.getCreatedTS());
//    } else if (workOrderType.equals(Statics.PRIORITY)) {
//      return getPriorityRank(workOrder.getCreatedTS());
//    } else {
//
//      return getNormalRank(workOrder.getCreatedTS());
//
//    }
//  }
//
//  protected static String getWorkOrderType(Long id) {
//    if (id % 3 == 0) {
//      if (id % 5 == 0) {
//        return Statics.MGMT_OVERRIDE;
//      }
//      return Statics.PRIORITY;
//    } else if (id % 5 == 0) {
//      return Statics.VIP;
//    } else {
//      return Statics.NORMAL;
//    }
//  }
//
//  protected static Long getMgmtOverrideRank(String date) throws ParseException {
//    DateService dateService = new DateService();
//    return dateService.getSecondsOnQueue(date);
//  }
//
//  protected static Long getVipRank(String date) throws ParseException {
//    DateService dateService = new DateService();
//    Long secondsOnQueue = dateService.getSecondsOnQueue(date);
//    double rank = Math.max(4, (Math.log(secondsOnQueue) / Math.log(2)) * secondsOnQueue);
//    return (long) rank;
//  }
//
//  protected static long getPriorityRank(String date) throws ParseException {
//    DateService dateService = new DateService();
//    Long secondsOnQueue = dateService.getSecondsOnQueue(date);
//    double rank = Math.max(3, (int) (Math.log(secondsOnQueue) / Math.log(2)) * secondsOnQueue);
//    return (long) rank;
//  }
//
//  protected static Long getNormalRank(String date) throws ParseException {
//    DateService dateService = new DateService();
//    return dateService.getSecondsOnQueue(date);
//  }


}
