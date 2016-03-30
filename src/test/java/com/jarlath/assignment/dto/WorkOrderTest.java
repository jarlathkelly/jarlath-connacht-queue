package com.jarlath.assignment.dto;

import com.jarlath.assignment.TestUtilities;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 29/03/2016.
 *
 * Unit tests for the WorkOrder class
 */
public class WorkOrderTest {
  TestUtilities testUtil = new TestUtilities();

  @Test
  public void test_Natural_And_Ranked_Ordering() {
    WorkOrder order1 = new WorkOrder(1l, testUtil.getADateString(1));
    WorkOrder order2 = new WorkOrder(2l, testUtil.getADateString(1));
    WorkOrder order3 = new WorkOrder(3l, testUtil.getADateString(1));
    WorkOrder order4 = new WorkOrder(4l, testUtil.getADateString(1));
    WorkOrder order5 = new WorkOrder(5l, testUtil.getADateString(1));
    WorkOrder order6 = new WorkOrder(6l, testUtil.getADateString(1));
    WorkOrder order7 = new WorkOrder(7l, testUtil.getADateString(1));
    WorkOrder order8 = new WorkOrder(8l, testUtil.getADateString(1));
    WorkOrder order9 = new WorkOrder(9l, testUtil.getADateString(1));
    WorkOrder order10 = new WorkOrder(10l, testUtil.getADateString(1));
    WorkOrder order12 = new WorkOrder(12l, testUtil.getADateString(1));
    WorkOrder order15 = new WorkOrder(15l, testUtil.getADateString(1));
    WorkOrder order20 = new WorkOrder(20l, testUtil.getADateString(1));
    WorkOrder order30 = new WorkOrder(30l, testUtil.getADateString(1));
    WorkOrder order40 = new WorkOrder(40l, testUtil.getADateString(1));
    WorkOrder order45 = new WorkOrder(45l, testUtil.getADateString(1));
    WorkOrder order50 = new WorkOrder(50l, testUtil.getADateString(1));
    WorkOrder order60 = new WorkOrder(60l, testUtil.getADateString(1));

    List<WorkOrder> l = new ArrayList<WorkOrder>();
    l.add(order1);
    l.add(order2);
    l.add(order3);
    l.add(order4);
    l.add(order5);
    l.add(order6);
    l.add(order7);
    l.add(order8);
    l.add(order9);
    l.add(order10);
    l.add(order12);
    l.add(order15);
    l.add(order20);
    l.add(order30);
    l.add(order40);
    l.add(order45);
    l.add(order50);
    l.add(order60);

    //Natural Order
    Collections.sort(l);
    assertTrue(l.get(0).getWorkOrderId() == 1);
    assertTrue(l.get(1).getWorkOrderId() == 2);
    assertTrue(l.get(2).getWorkOrderId() == 3);
    assertTrue(l.get(3).getWorkOrderId() == 4);
    assertTrue(l.get(4).getWorkOrderId() == 5);
    assertTrue(l.get(5).getWorkOrderId() == 6);
    assertTrue(l.get(6).getWorkOrderId() == 7);
    assertTrue(l.get(7).getWorkOrderId() == 8);
    assertTrue(l.get(8).getWorkOrderId() == 9);
    assertTrue(l.get(9).getWorkOrderId() == 10);
    assertTrue(l.get(10).getWorkOrderId() == 12);
    assertTrue(l.get(11).getWorkOrderId() == 15);
    assertTrue(l.get(12).getWorkOrderId() == 20);
    assertTrue(l.get(13).getWorkOrderId() == 30);
    assertTrue(l.get(14).getWorkOrderId() == 40);
    assertTrue(l.get(15).getWorkOrderId() == 45);
    assertTrue(l.get(16).getWorkOrderId() == 50);
    assertTrue(l.get(17).getWorkOrderId() == 60);

    Collections.sort(l, new WorkOrder());
    assertTrue(l.get(0).getWorkOrderId() == 15);
    assertTrue(l.get(1).getWorkOrderId() == 30);
    assertTrue(l.get(2).getWorkOrderId() == 45);
    assertTrue(l.get(3).getWorkOrderId() == 60);
    assertTrue(l.get(4).getWorkOrderId() == 5);
    assertTrue(l.get(5).getWorkOrderId() == 10);
    assertTrue(l.get(6).getWorkOrderId() == 20);
    assertTrue(l.get(7).getWorkOrderId() == 40);
    assertTrue(l.get(8).getWorkOrderId() == 50);
    assertTrue(l.get(9).getWorkOrderId() == 3);
    assertTrue(l.get(10).getWorkOrderId() == 6);
    assertTrue(l.get(11).getWorkOrderId() == 9);
    assertTrue(l.get(12).getWorkOrderId() == 12);
    assertTrue(l.get(13).getWorkOrderId() == 1);
    assertTrue(l.get(14).getWorkOrderId() == 2);
    assertTrue(l.get(15).getWorkOrderId() == 4);
    assertTrue(l.get(16).getWorkOrderId() == 7);
    assertTrue(l.get(17).getWorkOrderId() == 8);
  }


}
