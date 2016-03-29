package com.jarlath.assignment.service;

import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.exception.*;

import java.util.List;

/**
 * Created by jarlath.kelly on 29/03/2016.
 */
public interface WorkOrderQueueService {

  List<WorkOrder> retrieveWorkOrderQueue();
  WorkOrder enqueueWorkOrder(WorkOrder workOrder) throws WorkOrderExistsInQueueException,InvalidIdParameterException,InvalidTimestampParameterException;
  WorkOrder removeIdFromWorkOrderQueue(Long id) throws WorkOrderIdNotOnQueueException, InvalidIdParameterException;
  WorkOrder removeTopFromWorkOrderQueue();
  List<Long> retrieveWorkOrderedIdList();
  int retrieveIndexOfWorkOrderId(Long id) throws WorkOrderIdNotOnQueueException, InvalidIdParameterException;
  Long retrieveAverageWaitTime(String currentTs) throws TimeStampParsingException, InvalidTimestampParameterException;
}
