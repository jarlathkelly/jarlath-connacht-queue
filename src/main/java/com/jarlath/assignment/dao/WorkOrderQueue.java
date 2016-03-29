package com.jarlath.assignment.dao;

import com.jarlath.assignment.dto.WorkOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarlath.kelly on 28/03/2016.
 */
public class WorkOrderQueue {

  private static final List<WorkOrder> instance = new ArrayList<WorkOrder>();

  protected WorkOrderQueue() {
  }

  // Runtime initialisation By defualt ThreadSafe
  public static List<WorkOrder> getInstance() {
    return instance;
  }
}
