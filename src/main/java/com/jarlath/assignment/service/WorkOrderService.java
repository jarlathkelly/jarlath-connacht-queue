package com.jarlath.assignment.service;

import com.jarlath.assignment.dto.WorkOrder;

import java.text.ParseException;

/**
 * The {@link WorkOrderService} class defines the Interface for the WorkOrderService
 * implementations
 *
 * @author Jarlath Kelly
 * @see WorkOrderServiceImpl
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
