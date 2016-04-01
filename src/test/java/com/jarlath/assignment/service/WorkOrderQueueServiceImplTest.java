package com.jarlath.assignment.service;

import com.jarlath.assignment.TestUtilities;
import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.exception.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 29/03/2016.
 * <p>
 * Unit tests for the WorkOrderQueueServiceImpl class
 */
public class WorkOrderQueueServiceImplTest {

  WorkOrderQueueServiceImpl workOrderQueueService = new WorkOrderQueueServiceImpl();
  public final static Long ONE_HOUR = 3599L;
  public final static String BAD_DATE = "160419xxxx82183020";
  TestUtilities testUtil = new TestUtilities();

  @Test
  public void test_retrieveWorkOrderQueue() {
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    assertTrue(queue != null);
  }

  @Test
  public void test_enqueueWorkOrder() {
    clearQueue();
    WorkOrder workOrder = new WorkOrder("1234567", "27032016183015");
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
    WorkOrder workOrder = new WorkOrder("1234567", null);
    workOrderQueueService.enqueueWorkOrder(workOrder);
    clearQueue();
  }

  @Test(expected = WorkOrderExistsInQueueException.class)
  public void test_enqueueWorkOrder_WorkOrderExistsInQueueException() {
    clearQueue();
    WorkOrder workOrder = new WorkOrder("1", "27032016183015");
    workOrderQueueService.enqueueWorkOrder(workOrder);
    workOrderQueueService.enqueueWorkOrder(workOrder);
    clearQueue();
  }

  @Test
  public void test_removeIdFromWorkOrderQueue() throws WorkOrderIdNotOnQueueException {
    clearQueue();
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    int size = queue.size();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("1234567", "27032016183015"));
    assertTrue(queue.size() == size + 1);
    workOrderQueueService.removeIdFromWorkOrderQueue("1234567");
    assertTrue(queue.size() == size);
    clearQueue();
  }

  @Test(expected = WorkOrderIdNotOnQueueException.class)
  public void test_removeIdFromWorkOrderQueue_WorkOrderIdNotOnQueueException() {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("1234567", "27032016183015"));
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    workOrderQueueService.removeIdFromWorkOrderQueue("123444");
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
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("3", "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("5", "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("1", "27032016183015"));
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
  public void test_retrieveWorkOrderedIdList() {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("15000", "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("20000", "27032016183015"));
    List<Long> orderedList = workOrderQueueService.retrieveWorkOrderedIdList();
    assertTrue(orderedList.get(0) == 15000L);
    clearQueue();
  }

  @Test
  public void test_retrieveIndexOfWorkOrderId() throws WorkOrderIdNotOnQueueException {
    clearQueue();
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();

    workOrderQueueService.enqueueWorkOrder(new WorkOrder("29", "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("150", "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("10", "27032016183015"));
    WorkOrder workOrder = workOrderQueueService.retrieveIndexOfWorkOrderId("150");
    assertTrue(workOrder.getPosition() == 0);
    clearQueue();
  }

  @Test(expected = WorkOrderIdNotOnQueueException.class)
  public void test_retrieveIndexOfWorkOrderId_WorkOrderIdNotOnQueueException() {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("113", "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("1115", "27032016183015"));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("111", "27032016183015"));
    workOrderQueueService.retrieveIndexOfWorkOrderId("155");
    clearQueue();
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_retrieveIndexOfWorkOrderId_InvalidIdParameterException() {
    clearQueue();
    workOrderQueueService.retrieveIndexOfWorkOrderId("");
    clearQueue();
  }

  @Test
  public void test_retrieveAverageWaitTime() {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("77", testUtil.getADateString(2)));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("88", testUtil.getADateString(2)));
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("99", testUtil.getADateString(2)));
    assertTrue(workOrderQueueService.retrieveAverageWaitTime(testUtil.getADateString(1)) > ONE_HOUR);
    clearQueue();
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_retrieveAverageWaitTime_InvalidTimestampParameterException2() {
    clearQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("333", testUtil.getADateString(2)));
    workOrderQueueService.retrieveAverageWaitTime(BAD_DATE);
    clearQueue();
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_retrieveAverageWaitTime_InvalidTimestampParameterException() {
    clearQueue();
    workOrderQueueService.retrieveAverageWaitTime(null);
    clearQueue();
  }

  @Test(expected = NegativeDurationWaitTimeException.class)
  public void test_retrieveAverageWaitTime_NegativeDurationWaitTimeException() {
    clearQueue();
    List<WorkOrder> queue = workOrderQueueService.retrieveWorkOrderQueue();
    workOrderQueueService.enqueueWorkOrder(new WorkOrder("333", testUtil.getADateString(2)));
    workOrderQueueService.retrieveAverageWaitTime(testUtil.getADateString(6));
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

}


