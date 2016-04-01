package com.jarlath.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jarlath.assignment.TestUtilities;
import com.jarlath.assignment.exception.InvalidIdParameterException;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import com.jarlath.assignment.service.ValidationServiceImpl;
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

  @Test
  public void test_WorkOrder_constructor() {
    WorkOrder order = new WorkOrder("5", "30032016090056",8);
    assertTrue(order.getWorkOrderId().equals("5"));
    assertTrue(order.getCreatedTS().equals("30032016090056"));
    assertTrue(order.getPosition() == (8));
  }

  @Test
  public void test_WorkOrder_sets() {
    WorkOrder order = new WorkOrder();
    order.setPosition(8);
    order.setWorkOrderId("5");
    assertTrue(order.getWorkOrderId().equals("5"));
    assertTrue(order.getPosition() == (8));
  }

  @Test
  public void test_WorkOrder_compareTo_ById() {
    WorkOrder order1 = new WorkOrder("5", testUtil.getADateString(1));
    WorkOrder order2 = new WorkOrder("5", testUtil.getADateString(2));
    assertTrue(order1.compareTo(order2) == 0);
  }

  @Test (expected = ClassCastException.class)
  public void test_WorkOrder_compareTo_ClassCastException() {
    WorkOrder order1 = new WorkOrder("5", testUtil.getADateString(1));
    order1.compareTo(null);
  }

  @Test
  public void test_WorkOrder_compare_2MgMT_Override() {
    WorkOrder order1 = new WorkOrder("15", testUtil.getADateString(1));
    WorkOrder order2 = new WorkOrder("45", testUtil.getADateString(2));
    assertTrue(order1.compare(order1, order2) == 1);
  }

  @Test
  public void test_WorkOrder_compare_1MgMT_Override() {
    WorkOrder order1 = new WorkOrder("15", testUtil.getADateString(1));
    WorkOrder order2 = new WorkOrder("3", testUtil.getADateString(2));
    assertTrue(order1.compare(order1, order2) == -1);
  }

  @Test (expected = ClassCastException.class)
  public void test_WorkOrder_compare_ClassCastException() {
    WorkOrder order1 = new WorkOrder("5", testUtil.getADateString(1));
    order1.compare(order1,null);
  }

  @Test
  public void test_isValid() {
    WorkOrder order1 = new WorkOrder("5", testUtil.getADateString(1));
    assertTrue(order1.isValid(order1));
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_isValid_InvalidTs() {
    WorkOrder order1 = new WorkOrder("5", "xxxx");
    order1.isValid(order1);
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_isValid_InvalidId() {
    WorkOrder order1 = new WorkOrder("0", testUtil.getADateString(1));
    order1.isValid(order1);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_isValid_null_ts() {
    WorkOrder order1 = new WorkOrder("5",null);
    order1.isValid(order1);
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_isValid_null_id() {
    WorkOrder order1 = new WorkOrder(null,testUtil.getADateString(1));
    order1.isValid(order1);
  }

  @Test
  public void test_equals() {
    WorkOrder order1 = new WorkOrder("5",testUtil.getADateString(1));
    WorkOrder order2 = new WorkOrder("5",testUtil.getADateString(1));
    assertTrue(order1.equals(order2));
  }

  @Test
  public void test_equals_JustId() {
    WorkOrder order1 = new WorkOrder("5",testUtil.getADateString(1));
    WorkOrder order2 = new WorkOrder("5",testUtil.getADateString(2));
    assertTrue(order1.equals(order2));
  }

  @Test
  public void test_equals_False1() {
    WorkOrder order1 = new WorkOrder("5",testUtil.getADateString(1));
    assertTrue(!order1.equals(null));
  }

  @Test
  public void test_equals_False2() {
    WorkOrder order1 = new WorkOrder("5",testUtil.getADateString(1));
    WorkOrder order2 = new WorkOrder("6",testUtil.getADateString(2));
    assertTrue(!order1.equals(order2));
  }



}
