package com.jarlath.assignment.service;

import com.jarlath.assignment.dto.WorkOrder;

import java.text.ParseException;

/**
 * Created by jarlath.kelly on 29/03/2016.
 */
public interface WorkOrderService {

  int getComparisonReturnValue(Long value);
  Long getWorkOrderRank(String workOrderType, WorkOrder workOrder) throws ParseException;
  String getWorkOrderType(Long id);
  Long getMgmtOverrideRank(String date) throws ParseException;
  Long getVipRank(String date) throws ParseException;
  long getPriorityRank(String date) throws ParseException;
  Long getNormalRank(String date) throws ParseException;

}
