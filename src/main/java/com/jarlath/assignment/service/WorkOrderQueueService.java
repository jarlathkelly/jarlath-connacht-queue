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
 * Created by jarlath.kelly on 28/03/2016.
 */
@Service
public class WorkOrderQueueService {
  DateService dateService = new DateService();

  public WorkOrderQueueService(){

  }

  public List<WorkOrder> retrieveWorkOrderQueue(){
    return WorkOrderQueue.getInstance();

  }

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

  protected synchronized void  addToWorkOrderQueue(WorkOrder workOrder){
    List<WorkOrder> queue = WorkOrderQueue.getInstance();
    queue.add(workOrder);
  }

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


  protected synchronized void removeFromQueue(WorkOrder workorder){
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    queue.remove(workorder);
  }

  public List<Long> retrieveWorkOrderedIdList(){
    List<Long> result = new ArrayList<Long>();
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    Collections.sort(queue, new WorkOrder());
    for (WorkOrder item : queue) {
      result.add(item.getId());
    }
    return result;
  }

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

  public Long retrieveAverageWaitTime(String currentTs) throws TimeStampParsingException, InvalidTimestampParameterException {
    if(null == currentTs){
      throw new InvalidTimestampParameterException();
    }
    Long totalTime = 0L;
    Long meanTime = 0L;
    int count = 0;

    //deal with negatives
    List<WorkOrder> queue = retrieveWorkOrderQueue();
    count = queue.size();
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
