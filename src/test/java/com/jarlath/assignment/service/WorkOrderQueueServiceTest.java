package com.jarlath.assignment.service;

import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.exception.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 28/03/2016.
 */
public class WorkOrderQueueServiceTest {

  WorkOrderQueueServiceImpl workOrderQueueService = new WorkOrderQueueServiceImpl();
  public final static Long ONE_HOUR = 3599L;
  public final static String BAD_DATE = "160419xxxx82183020";

  @Test
  public void test_retrieveWorkOrderQueue() {
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    assertTrue(queue != null);
  }

  @Test
  public void test_enqueueWorkOrder() {
    clearQueue();
    WorkOrder workOrder = new WorkOrder(1234567L, "27032016183015");
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    int size = queue.size();
    workOrderQueueService.enqueueWorkOrder(workOrder);
    assertTrue(queue.size() == size + 1);
    clearQueue();
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_enqueueWorkOrder_InvalidIdParameterException() {
    clearQueue();
    WorkOrder workOrder = new WorkOrder(null, "27032016183015");
    workOrderQueueService.enqueueWorkOrder(workOrder);
    clearQueue();
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_enqueueWorkOrder_InvalidTimestampParameterException() {
    clearQueue();
    WorkOrder workOrder = new WorkOrder(1234567L, null);
    workOrderQueueService.enqueueWorkOrder(workOrder);
    clearQueue();
  }

  @Test(expected = WorkOrderExistsInQueueException.class)
  public void test_enqueueWorkOrder_WorkOrderExistsInQueueException() {
    clearQueue();
    WorkOrder workOrder = new WorkOrder(1L, "27032016183015");
    workOrderQueueService.enqueueWorkOrder(workOrder);
    workOrderQueueService.enqueueWorkOrder(workOrder);
    clearQueue();
  }

  @Test
  public void test_addToWorkOrderQueue() {
    clearQueue();
    WorkOrder workOrder = new WorkOrder(1234567L, "27032016183015");
    workOrderQueueService.addToWorkOrderQueue(workOrder);
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    assertTrue(queue.size() == 1);
    clearQueue();
  }

  @Test
  public void test_removeIdFromWorkOrderQueue() throws WorkOrderIdNotOnQueueException {
    clearQueue();
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    int size = queue.size();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(1234567L, "27032016183015"));
    assertTrue(queue.size() == size + 1);
    workOrderQueueService.removeIdFromWorkOrderQueue(1234567L);
    assertTrue(queue.size() == size);
    clearQueue();
  }

  @Test(expected = WorkOrderIdNotOnQueueException.class)
  public void test_removeIdFromWorkOrderQueue_WorkOrderIdNotOnQueueException() {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(1234567L, "27032016183015"));
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    workOrderQueueService.removeIdFromWorkOrderQueue(123444L);
    clearQueue();
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_removeIdFromWorkOrderQueue_InvalidIdParameterException() {
    clearQueue();
    workOrderQueueService.removeIdFromWorkOrderQueue(null);
  }

  @Test
  public void test_removeTopFromWorkOrderQueue() {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(3L, "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(15L, "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(1L, "27032016183015"));
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    assertTrue(queue.size() == 3);
    workOrderQueueService.removeTopFromWorkOrderQueue();
    assertTrue(queue.size() == 2);
    List<Long> idList = workOrderQueueService.retrieveWorkOrderedIdList();
    assertTrue(!idList.contains(15L));
    workOrderQueueService.removeTopFromWorkOrderQueue();
    assertTrue(queue.size() == 1);
    idList = workOrderQueueService.retrieveWorkOrderedIdList();
    assertTrue(!idList.contains(3L));
    clearQueue();
  }

  @Test
  public void test_removeFromQueue() {
    clearQueue();
    WorkOrder workOrder = new WorkOrder(1234567L, "27032016183015");
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    int size = queue.size();
    workOrderQueueService.addToWorkOrderQueue(workOrder);
    assertTrue(queue.size() == size + 1);
    workOrderQueueService.removeFromQueue(workOrder);
    assertTrue(queue.size() == size);
    clearQueue();
  }

 @Test
  public void test_retrieveWorkOrderedIdList() {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(15000L, "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(20000L, "27032016183015"));
    List<Long> orderedList = workOrderQueueService.retrieveWorkOrderedIdList();
    assertTrue(orderedList.get(0) == 15000L);
   clearQueue();
  }

  @Test
  public void test_retrieveIndexOfWorkOrderId() throws WorkOrderIdNotOnQueueException {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(29L, "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(150L, "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(10L, "27032016183015"));
    WorkOrder workOrder = workOrderQueueService.retrieveIndexOfWorkOrderId(150L);
    assertTrue(workOrder.getPosition() == 0);
    clearQueue();
  }

  @Test(expected = WorkOrderIdNotOnQueueException.class)
  public void test_retrieveIndexOfWorkOrderId_WorkOrderIdNotOnQueueException() {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(113L, "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(1115L, "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(111L, "27032016183015"));
    workOrderQueueService.retrieveIndexOfWorkOrderId(155L);
    clearQueue();
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_retrieveIndexOfWorkOrderId_InvalidIdParameterException() {
    clearQueue();
    workOrderQueueService.retrieveIndexOfWorkOrderId(null);
    clearQueue();
  }

  @Test
  public void test_retrieveAverageWaitTime() throws TimeStampParsingException {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(77L, getADateString(2)));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(88L, getADateString(2)));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(99L, getADateString(2)));
    assertTrue(workOrderQueueService.retrieveAverageWaitTime(getADateString(1)) > ONE_HOUR);
    clearQueue();
  }

  @Test(expected = TimeStampParsingException.class)
  public void test_retrieveAverageWaitTime_TimeStampParsingException() {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder(333L, getADateString(2)));
    workOrderQueueService.retrieveAverageWaitTime(BAD_DATE);
    clearQueue();
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_retrieveAverageWaitTime_InvalidTimestampParameterException() {
    clearQueue();
    workOrderQueueService.retrieveAverageWaitTime(null);
    clearQueue();
  }

  private void clearQueue() {
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    if (queue.size() > 0) {
      for (int i = 0; i < queue.size(); i++) {
        workOrderQueueService.removeTopFromWorkOrderQueue();
      }
    }
  }

  private String getADateString(int hour) {
    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
    Date currentDate = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(currentDate);
    cal.add(Calendar.HOUR, -hour);
    Date yesterday = cal.getTime();
    return formatter.format(yesterday);
  }

}


