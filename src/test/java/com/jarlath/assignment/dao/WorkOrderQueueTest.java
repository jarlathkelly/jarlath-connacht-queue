package com.jarlath.assignment.dao;

import com.jarlath.assignment.dto.WorkOrder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 29/03/2016.
 *
 * Unit tests for the WorkOrderQueue class
 */
public class WorkOrderQueueTest {

  @Test
  public void test_getInstance() {
    List queue = WorkOrderQueue.getInstance();
    assertTrue(queue instanceof List);
  }

  @Test
  public void test_removeElementFromQueue()  {
    WorkOrder order = new WorkOrder(67L,"160419821830555");
    List<WorkOrder> queue = WorkOrderQueue.getInstance();
    int size = queue.size();
    queue.add(order);
    assertTrue(queue.size() == size+1);
    queue.remove(order);
    assertTrue(queue.size() == size);
  }

  @Test
  public void test_add_RemoveElements()  {
    List queue = WorkOrderQueue.getInstance();
    WorkOrder order = new WorkOrder(67L,"160419821830555");
    int size = queue.size();
    queue.add(order);
    assertTrue(queue.size() == size+1);
    queue.remove(order);
    assertTrue(queue.size() == size);
  }


}
