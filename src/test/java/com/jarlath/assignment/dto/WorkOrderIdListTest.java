package com.jarlath.assignment.dto;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 29/03/2016.
 * <p>
 * Unit tests for the WorkOrderIdList class
 */
public class WorkOrderIdListTest {

  @Test
  public void test_DefaultConstructor() {
    WorkOrderIdList list = new WorkOrderIdList();
    assertTrue(list instanceof WorkOrderIdList);
  }

  @Test
  public void test_Constructor() {
    List<Long> workOrderIdList = new ArrayList<>();
    WorkOrderIdList list = new WorkOrderIdList(workOrderIdList);
    assertTrue(list instanceof WorkOrderIdList);
  }

  @Test
  public void test_getWorkOrderIdList() {
    List<Long> workOrderIdList = new ArrayList<>();
    WorkOrderIdList list = new WorkOrderIdList(workOrderIdList);
    assertTrue(list.getWorkOrderIdList() instanceof List);
  }

  @Test
  public void setWorkOrderIdList() {
    List<Long> workOrderIdList = new ArrayList<>();
    WorkOrderIdList list = new WorkOrderIdList();
    list.setWorkOrderIdList(workOrderIdList);
    assertTrue(list.getWorkOrderIdList() instanceof List);
  }

}