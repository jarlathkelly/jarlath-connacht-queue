package com.jarlath.assignment.dto;

import com.jarlath.assignment.TestUtilities;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 29/03/2016.
 * <p>
 * Unit tests for the WorkOrder class
 */
public class WorkOrderTest {
  TestUtilities testUtil = new TestUtilities();

  @Test
  public void test_Natural_And_Ranked_Ordering() {

    WorkOrder order1 = new WorkOrder("5", testUtil.getADateString(1));
    WorkOrder order2 = new WorkOrder("8", testUtil.getADateString(1));
    WorkOrder order3 = new WorkOrder("9", testUtil.getADateString(1));
    WorkOrder order4 = new WorkOrder("15", testUtil.getADateString(1));

    List<WorkOrder> l = new ArrayList<WorkOrder>();
    l.add(order1);
    l.add(order2);
    l.add(order3);
    l.add(order4);

    Collections.sort(l, new WorkOrder());
    assertTrue(l.get(0).getWorkOrderId() == "15");
    assertTrue(l.get(1).getWorkOrderId() == "5");
    assertTrue(l.get(2).getWorkOrderId() == "9");
    assertTrue(l.get(3).getWorkOrderId() == "8");
  }


}
