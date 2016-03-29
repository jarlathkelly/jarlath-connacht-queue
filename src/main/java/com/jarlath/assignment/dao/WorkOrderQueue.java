package com.jarlath.assignment.dao;

import com.jarlath.assignment.dto.WorkOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link WorkOrderQueue} represents a Singleton Queue Object to queue
 * {@link WorkOrder} objects.
 *
 * @author Jarlath Kelly
 * @see WorkOrder
 */
@Repository
public class WorkOrderQueue {

  private static final List<WorkOrder> instance = new ArrayList<>();

  protected WorkOrderQueue() {
  }

  /**
   * Provide runtime initilisation of the singleton Queue.
   * This type of instantiation is threadsafe by default.
   *
   * @return List of WorkOrders
   */
  public static List<WorkOrder> getInstance() {
    return instance;
  }
}
