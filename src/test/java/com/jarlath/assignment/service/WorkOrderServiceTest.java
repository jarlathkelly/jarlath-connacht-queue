package com.jarlath.assignment.service;

import com.jarlath.assignment.TestUtilities;
import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.util.Statics;
import org.junit.Test;
import java.text.ParseException;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 28/03/2016.
 */
public class WorkOrderServiceTest {
  WorkOrderServiceImpl WorkOrderService = new WorkOrderServiceImpl();

  public final static String BAD_DATE = "160419xxxx82183020";
  public final static Long ONE_HOUR = 3599L;
  TestUtilities testUtil = new TestUtilities();

  @Test
  public void test_getNormalRank() throws ParseException {
    String testDate = testUtil.getADateString(1);
    Long seconds = WorkOrderService.getNormalRank(testDate);
    assertTrue(seconds > ONE_HOUR);
  }

  @Test(expected = ParseException.class)
  public void test_getNormalRank_ParseException() throws ParseException {
    String testDate = BAD_DATE;
    WorkOrderService.getNormalRank(testDate);
  }

  @Test
  public void test_getMgmtOverrideRank() throws ParseException {
    String testDate = testUtil.getADateString(1);
    Long seconds = WorkOrderService.getMgmtOverrideRank(testDate);
    assertTrue(seconds > ONE_HOUR);
  }

  @Test(expected = ParseException.class)
  public void test_getMgmtOverrideRank_ParseException() throws ParseException {
    String testDate = BAD_DATE;
    WorkOrderService.getMgmtOverrideRank(testDate);
  }

  @Test
  public void test_getVipRank() throws ParseException {
    String testDate = testUtil.getADateString(1);
    Long rank = WorkOrderService.getVipRank(testDate);
    assertTrue(rank == 42529L);
  }

  @Test
  public void test_getPriorityRank() throws ParseException {
    String testDate = testUtil.getADateString(1);
    Long rank = WorkOrderService.getPriorityRank(testDate);
    assertTrue(rank == 39600L);
  }

  @Test
  public void test_getWorkOrderType_MgmtOverride() {
    Long id = 15L;
    String rank = WorkOrderService.getWorkOrderType(id);
    assertTrue(rank.equals(Statics.MGMT_OVERRIDE));
  }

  @Test
  public void test_getWorkOrderType_Vip() {
    Long id = 5L;
    String rank = WorkOrderService.getWorkOrderType(id);
    assertTrue(rank.equals(Statics.VIP));
  }

  @Test
  public void test_getWorkOrderType_Priority() {
    Long id = 3L;
    String rank = WorkOrderService.getWorkOrderType(id);
    assertTrue(rank.equals(Statics.PRIORITY));
  }

  @Test
  public void test_getWorkOrderType_Normal() {
    Long id = 4L;
    String rank = WorkOrderService.getWorkOrderType(id);
    assertTrue(rank.equals(Statics.NORMAL));
  }

  @Test
  public void test_getWorkOrderRank_MgmtOverride() throws ParseException {
    WorkOrder workOrder = new WorkOrder(11111l, testUtil.getADateString(1));
    Long rank = WorkOrderService.getWorkOrderRank(Statics.MGMT_OVERRIDE, workOrder);
    assertTrue(rank > ONE_HOUR);
  }

  @Test
  public void test_getWorkOrderRank_Vip() throws ParseException {
    WorkOrder workOrder = new WorkOrder(11111l, testUtil.getADateString(1));
    Long rank = WorkOrderService.getWorkOrderRank(Statics.VIP, workOrder);
    assertTrue(rank > 42528);
  }

  @Test
  public void test_getWorkOrderRank_Priority() throws ParseException {
    WorkOrder workOrder = new WorkOrder(11111l, testUtil.getADateString(1));
    Long rank = WorkOrderService.getWorkOrderRank(Statics.PRIORITY, workOrder);
    assertTrue(rank > 39599);
  }

  @Test
  public void test_getWorkOrderRank_Normal() throws ParseException {
    WorkOrder workOrder = new WorkOrder(11111l, testUtil.getADateString(1));
    Long rank = WorkOrderService.getWorkOrderRank(Statics.NORMAL, workOrder);
    assertTrue(rank > ONE_HOUR);
  }

  @Test(expected = ParseException.class)
  public void test_getWorkOrderRank_Normal_ParseException() throws ParseException {
    WorkOrder workOrder = new WorkOrder(11111l, BAD_DATE);
    WorkOrderService.getWorkOrderRank(Statics.NORMAL, workOrder);
  }

}
