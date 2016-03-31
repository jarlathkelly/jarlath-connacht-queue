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

  Long getWorkOrderRank(final String workOrderType, final WorkOrder workOrder);

  String getWorkOrderType(final String id);

  Long getMgmtOverrideRank(final String date);

  Long getVipRank(final String date);

  long getPriorityRank(final String date);

  Long getNormalRank(final String date);

}
