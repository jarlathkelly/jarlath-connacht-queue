package com.jarlath.assignment.service;

import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.util.Statics;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * The {@link WorkOrderServiceImpl} is the concrete implementation for the WorkOrderService
 * Interface. Provides Work Order related services for use within the Work Order application.
 *
 * @author Jarlath Kelly
 * @see WorkOrderService
 */
@Service
public class WorkOrderServiceImpl implements WorkOrderService {

  public WorkOrderServiceImpl() {

  }

  /**
   * Utility method to facilitate the comparison of WorkOrder Objects.
   * returns -1 if supplied value is a positive value.
   * returns 1 if supplied value is a negative value.
   * returns 0 if supplied value is zero.
   *
   * @param value supplied for analysis
   * @return int
   */
  public int getComparisonReturnValue(final Long value) {
    if (value > 0) {
      return -1;
    } else if (value < 0) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Gets the rank of a Work Order. Leverages the getVipRank(),getPriorityRank and
   * getNormalRank() class methods to calculate this rank. The method used
   * for the calculation is dictated by the workOrderType supplied.
   *
   * @param workOrderType Type of Work Order Request
   * @param workOrder     instance of WorkOrder
   * @return Long rank of Work Order
   */
  public Long getWorkOrderRank(final String workOrderType,final  WorkOrder workOrder) throws ParseException {

    switch (workOrderType) {
      case Statics.VIP:
        return getVipRank(workOrder.getCreatedTS());
      case Statics.PRIORITY:
        return getPriorityRank(workOrder.getCreatedTS());
      default:
        return getNormalRank(workOrder.getCreatedTS());
    }
  }

  /**
   * Gets the type of a Work Order. The type of Work Order is calculated
   * by applying the 4 rules below to the supplied Id:
   * (1) IDs divisible by 3 are priority.
   * (2) IDs divisible by 5 are VIP.
   * (3) IDs divisible by both 3 and 5 are management override.
   * (4) IDs not divisible by 3 or 5 are normal.
   *
   * @param orderId identifier of Work Order
   * @return String Work Order Type
   */
  public String getWorkOrderType(final String orderId) {
    Long id = Long.parseLong(orderId);
    if (id % 3 == 0) {
      if (id % 5 == 0) {
        return Statics.MGMT_OVERRIDE;
      }
      return Statics.PRIORITY;
    } else if (id % 5 == 0) {
      return Statics.VIP;
    } else {
      return Statics.NORMAL;
    }
  }

  /**
   * Gets the Rank for Work Orders of Type MANAGEMENT OVERRIDE.
   * The rank is equal to the number of seconds on queue
   *
   * @param date date Work Order was queued
   * @return Long Rank of Work Order
   */
  public Long getMgmtOverrideRank(final String date) throws ParseException {
    DateServiceImpl dateService = new DateServiceImpl();
    return dateService.getSecondsOnQueue(date);
  }

  /**
   * Gets the Rank for Work Orders of Type VIP.
   * The rank is equal to max(3, n log n) where n is no of seconds on queue
   *
   * @param date date Work Order was queued
   * @return Long Rank of Work Order
   */
  public Long getVipRank(final String date) throws ParseException {
    DateServiceImpl dateService = new DateServiceImpl();
    Long secondsOnQueue = dateService.getSecondsOnQueue(date);
    double rank = Math.max(4, (Math.log(secondsOnQueue) / Math.log(2)) * secondsOnQueue);
    return (long) rank;
  }

  /**
   * Gets the Rank for Work Orders of Type PRIORITY.
   * The rank is equal to max(4, 2n log n) where n is no of seconds on queue
   *
   * @param date date Work Order was queued
   * @return Long Rank of Work Order
   */
  public long getPriorityRank(final String date) throws ParseException {
    DateServiceImpl dateService = new DateServiceImpl();
    Long secondsOnQueue = dateService.getSecondsOnQueue(date);
    double rank = Math.max(3, (int) (Math.log(secondsOnQueue) / Math.log(2)) * secondsOnQueue);
    return (long) rank;
  }

  /**
   * Gets the Rank for Work Orders of Type NORMAL.
   * The rank is equal to the no of seconds on queue
   *
   * @param date date Work Order was queued
   * @return Long Rank of Work Order
   */
  public Long getNormalRank(final String date) throws ParseException {
    DateServiceImpl dateService = new DateServiceImpl();
    return dateService.getSecondsOnQueue(date);
  }
}
