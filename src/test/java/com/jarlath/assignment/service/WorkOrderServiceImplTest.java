package com.jarlath.assignment.service;

import com.jarlath.assignment.TestUtilities;
import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import com.jarlath.assignment.util.Statics;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 29/03/2016.
 * <p>
 * Unit tests for the WorkOrderServiceImpl class
 */
public class WorkOrderServiceImplTest {
  WorkOrderServiceImpl WorkOrderService = new WorkOrderServiceImpl();

  public final static String BAD_DATE = "160419xxxx82183020";
  public final static Long ONE_HOUR = 3599L;
  TestUtilities testUtil = new TestUtilities();

  @Test
  public void test_getNormalRank() {
    String testDate = testUtil.getADateString(1);
    Long seconds = WorkOrderService.getNormalRank(testDate);
    assertTrue(seconds > ONE_HOUR);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getNormalRank_InvalidTimestampParameterException() {
    String testDate = BAD_DATE;
    WorkOrderService.getNormalRank(testDate);
  }

  @Test
  public void test_getMgmtOverrideRank() {
    String testDate = testUtil.getADateString(1);
    Long seconds = WorkOrderService.getMgmtOverrideRank(testDate);
    assertTrue(seconds > ONE_HOUR);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getMgmtOverrideRank_InvalidTimestampParameterException() {
    String testDate = BAD_DATE;
    WorkOrderService.getMgmtOverrideRank(testDate);
  }

  @Test
  public void test_getVipRank() {
    String testDate = testUtil.getADateString(1);
    Long rank = WorkOrderService.getVipRank(testDate);
    assertTrue(rank == 42529L);
  }

  @Test
  public void test_getPriorityRank() {
    String testDate = testUtil.getADateString(1);
    Long rank = WorkOrderService.getPriorityRank(testDate);
    assertTrue(rank == 39600L);
  }

  @Test
  public void test_getWorkOrderType_MgmtOverride() {
    String rank = WorkOrderService.getWorkOrderType("15");
    assertTrue(rank.equals(Statics.MGMT_OVERRIDE));
  }

  @Test
  public void test_getWorkOrderType_Vip() {
    String rank = WorkOrderService.getWorkOrderType("5");
    assertTrue(rank.equals(Statics.VIP));
  }

  @Test
  public void test_getWorkOrderType_Priority() {
    String rank = WorkOrderService.getWorkOrderType("3");
    assertTrue(rank.equals(Statics.PRIORITY));
  }

  @Test
  public void test_getWorkOrderType_Normal() {
    String rank = WorkOrderService.getWorkOrderType("4");
    assertTrue(rank.equals(Statics.NORMAL));
  }

  @Test
  public void test_getWorkOrderRank_MgmtOverride() {
    WorkOrder workOrder = new WorkOrder("11111", testUtil.getADateString(1));
    Long rank = WorkOrderService.getWorkOrderRank(Statics.MGMT_OVERRIDE, workOrder);
    assertTrue(rank > ONE_HOUR);
  }

  @Test
  public void test_getWorkOrderRank_Vip() {
    WorkOrder workOrder = new WorkOrder("11111", testUtil.getADateString(1));
    Long rank = WorkOrderService.getWorkOrderRank(Statics.VIP, workOrder);
    assertTrue(rank > 42528);
  }

  @Test
  public void test_getWorkOrderRank_Priority() {
    WorkOrder workOrder = new WorkOrder("11111", testUtil.getADateString(1));
    Long rank = WorkOrderService.getWorkOrderRank(Statics.PRIORITY, workOrder);
    assertTrue(rank > 39599);
  }

  @Test
  public void test_getWorkOrderRank_Normal() {
    WorkOrder workOrder = new WorkOrder("11111", testUtil.getADateString(1));
    Long rank = WorkOrderService.getWorkOrderRank(Statics.NORMAL, workOrder);
    assertTrue(rank > ONE_HOUR);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getWorkOrderRank_Normal_InvalidTimestampParameterException() {
    WorkOrder workOrder = new WorkOrder("11111", BAD_DATE);
    WorkOrderService.getWorkOrderRank(Statics.NORMAL, workOrder);
  }

}
