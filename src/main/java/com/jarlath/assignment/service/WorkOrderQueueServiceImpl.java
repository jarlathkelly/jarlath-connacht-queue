package com.jarlath.assignment.service;

import com.jarlath.assignment.dao.WorkOrderQueue;
import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.exception.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@link WorkOrderQueueServiceImpl} is the concrete implementation for the WorkOrderQueueService
 * Interface. Provides WorkOrderQueue related services for use within the Work Order application.
 *
 * @author Jarlath Kelly
 * @see WorkOrderQueueService
 */
@Service
public class WorkOrderQueueServiceImpl implements WorkOrderQueueService {
  DateServiceImpl dateService = new DateServiceImpl();

  public WorkOrderQueueServiceImpl() {

  }

  public List<WorkOrder> retrieveWorkOrderQueue() {
    return WorkOrderQueue.getInstance();

  }

  /**
   * Queues a Work Order Request object onto the Singleton WorkOrderQueue.
   * Performs basic validation on the supplied WorkOrder and also
   * ensures the queue does not already exist on the queue before enqueueing the
   * Work Order Object.
   *
   * @param workOrder to be enqueued
   * @return WorkOrder just enqueued
   */
  public WorkOrder enqueueWorkOrder(final WorkOrder workOrder) throws WorkOrderExistsInQueueException, InvalidIdParameterException, InvalidTimestampParameterException {
    if (null == workOrder.getWorkOrderId()) {
      throw new InvalidIdParameterException();
    }
    if (null == workOrder.getCreatedTS()) {
      throw new InvalidTimestampParameterException();
    }
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    synchronized (queue) {
      if (queue.contains(workOrder)) {
        throw new WorkOrderExistsInQueueException(workOrder.getWorkOrderId());
      } else {
        queue.add(workOrder);
      }
    }

    return workOrder;
  }

  /**
   * Dequeues a Work Order Request object from the Singleton WorkOrderQueue.
   * Performs basic validation on the supplied WorkOrder and also
   * ensures the workOrder does exist on the queue before dequeueing the
   * Work Order Object.
   *
   * @param id of the WorkOrder to be dequeued
   * @return WorkOrder just dequeued
   */
  public WorkOrder removeIdFromWorkOrderQueue(final String id) throws WorkOrderIdNotOnQueueException, InvalidIdParameterException {
    if (null == id) {
      throw new InvalidIdParameterException();
    }
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    WorkOrder workOrder = new WorkOrder();
    workOrder.setWorkOrderId(id);
    synchronized (queue) {
      if (!queue.contains(workOrder)) {
        throw new WorkOrderIdNotOnQueueException(id);
      } else {
        workOrder = queue.get(queue.indexOf(workOrder));
        queue.remove(workOrder);
      }
    }

    return workOrder;

  }

  /**
   * Dequeues the top ranked Work Order Request object
   * from the Singleton WorkOrderQueue.
   *
   * @return WorkOrder just dequeued
   */
  public WorkOrder removeTopFromWorkOrderQueue() {
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    WorkOrder workOrder = new WorkOrder();
    if (queue.size() > 0) {
      synchronized (queue) {
        Collections.sort(queue, new WorkOrder());
        workOrder = queue.get(0);
        queue.remove(workOrder);
      }
    }
    return workOrder;
  }

  /**
   * Retrieves a prioritised List of Work Order Request objects
   * in decreasing order of Rank.
   *
   * @return List of WorkOrder Id's sorted by Rank highest to lowest.
   */
  public List<Long> retrieveWorkOrderedIdList() {
    List<Long> result = new ArrayList<>();
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    synchronized (queue) {
      Collections.sort(queue, new WorkOrder());
      for (WorkOrder item : queue) {
        result.add(Long.parseLong(item.getWorkOrderId()));
      }
    }
    return result;
  }

  /**
   * Retrieves the index position of a Work Order Request currently on the queue.
   * Performs basic validation on the supplied Id and also ensures the Id is actually
   * present on the queue.
   *
   * @param id identifier Work Order Request
   * @return int index of supplied Work Order Id on the Queue.
   */
  public WorkOrder retrieveIndexOfWorkOrderId(final String id) throws WorkOrderIdNotOnQueueException, InvalidIdParameterException {
    if (null == id || id == "") {
      throw new InvalidIdParameterException();
    }
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    Collections.sort(queue, new WorkOrder());
    WorkOrder workOrder = new WorkOrder();
    workOrder.setWorkOrderId(id);
    synchronized (queue) {
      if (!queue.contains(workOrder)) {
        throw new WorkOrderIdNotOnQueueException();
      } else {
        int index = queue.indexOf(workOrder);
        workOrder = queue.get(index);
        workOrder.setPosition(index);
      }
    }
    return workOrder;

  }

  /**
   * Retrieves the average waittime of the Work Orders on the Queue
   *
   * @param currentTs String Date time representation of format DDMMYYYYHHMMSS
   * @return Long average waittime in seconds of all Work Orders on queue.
   */
  public Long retrieveAverageWaitTime(final String currentTs) throws InvalidTimestampParameterException, NegativeDurationWaitTimeException {
    if (null == currentTs) {
      throw new InvalidTimestampParameterException();
    }
    Long totalTime = 0L;
    Long meanTime = 0L;

    List<WorkOrder> queue = retrieveWorkOrderQueue();
    int count = queue.size();
    synchronized (queue) {
      for (WorkOrder item : queue) {
        totalTime = totalTime + dateService.getSecondsOnQueueUntilSpecifiedTime(item.getCreatedTS(), currentTs);
          meanTime = totalTime / count;
        if (meanTime < 0) {
          throw new NegativeDurationWaitTimeException();
        }
      }
    }

    return meanTime;
  }


}
