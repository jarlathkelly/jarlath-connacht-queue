package com.jarlath.assignment.dto;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 29/03/2016.
 * <p>
 * Unit tests for the WorkOrderQueueParamTest class
 */
public class WorkOrderQueueParamTest {
  @Test
  public void test_DefaultConstructor() {
    WorkOrderQueueParam param = new WorkOrderQueueParam();
    assertTrue(param instanceof WorkOrderQueueParam);
  }

  @Test
  public void test_Constructor() {
    Long waitTime = 8888L;
    WorkOrderQueueParam param = new WorkOrderQueueParam(waitTime);
    assertTrue(param instanceof WorkOrderQueueParam);
  }

  @Test
  public void test_getWaitTime() {
    Long waitTime = 8888L;
    WorkOrderQueueParam param = new WorkOrderQueueParam(waitTime);
    assertTrue(param.getWaitTime() instanceof Long);
  }

  @Test
  public void test_setWaitTime() {
    Long waitTime = 8888L;    WorkOrderQueueParam param = new WorkOrderQueueParam();
    param.setWaitTime(waitTime);
    assertTrue(param.getWaitTime() instanceof Long);
  }

}
