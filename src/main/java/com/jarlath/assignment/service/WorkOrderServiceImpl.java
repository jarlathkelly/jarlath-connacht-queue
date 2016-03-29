package com.jarlath.assignment.service;

import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.util.Statics;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Created by jarlath.kelly on 28/03/2016.
 */
@Service
public class WorkOrderServiceImpl implements WorkOrderService{

  public WorkOrderServiceImpl(){

  }

  public int getComparisonReturnValue(Long value) {
    if (value > 0) {
      return -1;
    } else if (value < 0) {
      return 1;
    } else {
      return 0;
    }
  }

  public Long getWorkOrderRank(String workOrderType, WorkOrder workOrder) throws ParseException {
    Long rank = 0L;

    if (workOrderType.equals(Statics.VIP)) {
      return getVipRank(workOrder.getCreatedTS());
    } else if (workOrderType.equals(Statics.PRIORITY)) {
      return getPriorityRank(workOrder.getCreatedTS());
    } else {

      return getNormalRank(workOrder.getCreatedTS());

    }
  }

  public String getWorkOrderType(Long id) {
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

  public Long getMgmtOverrideRank(String date) throws ParseException {
    DateServiceImpl dateService = new DateServiceImpl();
    return dateService.getSecondsOnQueue(date);
  }

  public Long getVipRank(String date) throws ParseException {
    DateServiceImpl dateService = new DateServiceImpl();
    Long secondsOnQueue = dateService.getSecondsOnQueue(date);
    double rank = Math.max(4, (Math.log(secondsOnQueue) / Math.log(2)) * secondsOnQueue);
    return (long) rank;
  }

  public long getPriorityRank(String date) throws ParseException {
    DateServiceImpl dateService = new DateServiceImpl();
    Long secondsOnQueue = dateService.getSecondsOnQueue(date);
    double rank = Math.max(3, (int) (Math.log(secondsOnQueue) / Math.log(2)) * secondsOnQueue);
    return (long) rank;
  }

  public Long getNormalRank(String date) throws ParseException {
    DateServiceImpl dateService = new DateServiceImpl();
    return dateService.getSecondsOnQueue(date);
  }
}
