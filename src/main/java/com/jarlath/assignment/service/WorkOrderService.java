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

  int getComparisonReturnValue(final Long value);

  Long getWorkOrderRank(final String workOrderType,final  WorkOrder workOrder) throws ParseException;

  String getWorkOrderType(final Long id);

  Long getMgmtOverrideRank(final String date) throws ParseException;

  Long getVipRank(final String date) throws ParseException;

  long getPriorityRank(final String date) throws ParseException;

  Long getNormalRank(final String date) throws ParseException;

}
