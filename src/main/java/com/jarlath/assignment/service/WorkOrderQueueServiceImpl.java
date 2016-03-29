package com.jarlath.assignment.service;

import com.jarlath.assignment.dao.WorkOrderQueue;
import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.exception.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@link WorkOrderQueueServiceImpl} is the concrete implementation for the WorkOrderQueueService
 * Interface. Provides WorkOrderQueue related services for use within the Work Order application.
 *
 * @author  Jarlath Kelly
 * @see WorkOrderQueueService
 */
@Service
public class WorkOrderQueueServiceImpl implements WorkOrderQueueService {
  DateServiceImpl dateService = new DateServiceImpl();

  public WorkOrderQueueServiceImpl(){

  }

  public List<WorkOrder> retrieveWorkOrderQueue(){
    return WorkOrderQueue.getInstance();

  }

  /**
   * Queues a Work Order Request object onto the Singleton WorkOrderQueue.
   * Performs basic validation on the supplied WorkOrder and also
   * ensures the queue does not already exist on the queue before enqueueing the
   * Work Order Object.
   *
   *
   * @param workOrder to be enqueued
   * @return WorkOrder just enqueued
   */
  public WorkOrder enqueueWorkOrder(WorkOrder workOrder) throws WorkOrderExistsInQueueException,InvalidIdParameterException,InvalidTimestampParameterException  {
    if(null == workOrder.getId()){
      throw new InvalidIdParameterException();
    }
    if(null == workOrder.getCreatedTS()){
      throw new InvalidTimestampParameterException();
    }
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    for (WorkOrder order : queue) {
      if (order.getId().equals(workOrder.getId())) {
        throw new WorkOrderExistsInQueueException(workOrder.getId());
      }
    }
    addToWorkOrderQueue(workOrder);
    return workOrder;
  }

  /**
   * Enqueues the Work Order Request object
   * from the Singleton WorkOrderQueue in a synchronised manner.
   *
   * @param workOrder WorkOrder to be enqueued
   */
  protected synchronized void  addToWorkOrderQueue(WorkOrder workOrder){
    List<WorkOrder> queue = WorkOrderQueue.getInstance();
    queue.add(workOrder);
  }

  /**
   * Dequeues a Work Order Request object from the Singleton WorkOrderQueue.
   * Performs basic validation on the supplied WorkOrder and also
   * ensures the workOrder does exist on the queue before dequeueing the
   * Work Order Object.
   *
   *
   * @param id of the WorkOrder to be dequeued
   * @return WorkOrder just dequeued
   */
  public WorkOrder removeIdFromWorkOrderQueue(Long id) throws WorkOrderIdNotOnQueueException, InvalidIdParameterException {
    if(null == id){
      throw new InvalidIdParameterException();
    }
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    WorkOrder workOrder = new WorkOrder();
    for (WorkOrder item : queue) {
      if (item.getId().equals(id)) {
        workOrder = item;
        break;
      }
    }
    if (null == workOrder.getId() ) {
      throw new WorkOrderIdNotOnQueueException(id);
    }
    removeFromQueue(workOrder);
    return workOrder;

  }

  /**
   * Dequeues the top ranked Work Order Request object
   * from the Singleton WorkOrderQueue.
   *
   * @return WorkOrder just dequeued
   */
  public WorkOrder removeTopFromWorkOrderQueue(){
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    WorkOrder workOrder = new WorkOrder();
    if (queue.size() > 0) {
      Collections.sort(queue, new WorkOrder());
      workOrder = queue.get(0);
      removeFromQueue(queue.get(0));
    }
    return workOrder;
  }


  /**
   * Dequeues the Work Order Request object
   * from the Singleton WorkOrderQueue in a synchronised manner.
   *
   * @param workorder WorkOrder to be dequeued
   */
  protected synchronized void removeFromQueue(WorkOrder workorder){
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    queue.remove(workorder);
  }

  /**
   * Retrieves a prioritised List of Work Order Request objects
   * in decreasing order of Rank.
   *
   * @return List of WorkOrder Id's sorted by Rank highest to lowest.
   */
  public List<Long> retrieveWorkOrderedIdList(){
    List<Long> result = new ArrayList<Long>();
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    Collections.sort(queue, new WorkOrder());
    for (WorkOrder item : queue) {
      result.add(item.getId());
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
  public int retrieveIndexOfWorkOrderId(Long id) throws WorkOrderIdNotOnQueueException, InvalidIdParameterException {
    if(null == id){
      throw new InvalidIdParameterException();
    }
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    Collections.sort(queue, new WorkOrder());
    int index = 0;
    boolean workOrderFound = false;
    for (WorkOrder item : queue) {
      if (item.getId().equals(id)) {
        workOrderFound = true;
        break;
      }
      index = index + 1;
    }
    if (!workOrderFound) {
      throw new WorkOrderIdNotOnQueueException();
    }
    return index;
  }

  /**
   * Retrieves the average waittime of the Work Orders on the Queue
   *
   * @param currentTs String Date time representation of format DDMMYYYYHHMMSS
   * @return Long average waittime in seconds of all Work Orders on queue.
   */
  public Long retrieveAverageWaitTime(String currentTs) throws TimeStampParsingException, InvalidTimestampParameterException {
    if(null == currentTs){
      throw new InvalidTimestampParameterException();
    }
    Long totalTime = 0L;
    Long meanTime = 0L;

    List<WorkOrder> queue = retrieveWorkOrderQueue();
    int count = queue.size();
    for (WorkOrder item : queue) {
      try {
        totalTime = totalTime + dateService.getSecondsOnQueueUntilSpecifiedTime(item.getCreatedTS(), currentTs);
        if (count != 0 && totalTime != 0) {
          meanTime = totalTime / count;
        }
      } catch (ParseException pe) {
        throw new TimeStampParsingException(currentTs);
      }
    }

    return meanTime;
  }


}
