package com.jarlath.assignment.service;

import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.exception.*;

import java.util.List;

/**
 * The {@link WorkOrderQueueService} class defines the Interface for the WorkOrderQueueService
 * implementations
 *
 * @author Jarlath Kelly
 * @see WorkOrderQueueServiceImpl
 */
public interface WorkOrderQueueService {

  List<WorkOrder> retrieveWorkOrderQueue();

  WorkOrder enqueueWorkOrder(WorkOrder workOrder) throws WorkOrderExistsInQueueException, InvalidIdParameterException, InvalidTimestampParameterException;

  WorkOrder removeIdFromWorkOrderQueue(Long id) throws WorkOrderIdNotOnQueueException, InvalidIdParameterException;

  WorkOrder removeTopFromWorkOrderQueue();

  List<Long> retrieveWorkOrderedIdList();

  WorkOrder retrieveIndexOfWorkOrderId(Long id) throws WorkOrderIdNotOnQueueException, InvalidIdParameterException;

  Long retrieveAverageWaitTime(String currentTs) throws TimeStampParsingException, InvalidTimestampParameterException;
}
