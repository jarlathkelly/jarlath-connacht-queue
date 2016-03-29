package com.jarlath.assignment.controller;

import java.util.List;

import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.service.ValidationServiceImpl;
import com.jarlath.assignment.service.WorkOrderQueueServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@link WorkOrderController} handles the HTTP requests for the WorkOrder Restful Interface.
 * The class performs basic validation on all incoming requests via the {@link ValidationServiceImpl}.
 * All actions to be performed on the WorkOrderQueue are done so via the {@link WorkOrderQueueServiceImpl}.
 *
 * @author Jarlath Kelly
 * @see RestController
 * @see ValidationServiceImpl
 * @see WorkOrderQueueServiceImpl
 */

@RestController
public class WorkOrderController {

  ValidationServiceImpl validationService = new ValidationServiceImpl();
  WorkOrderQueueServiceImpl workOrderQueueService = new WorkOrderQueueServiceImpl();

  /**
   * This endpoint adds a Work Order Request to the the queue (enqueue).
   * <p>
   * This endpoint accepts two parameters.
   * The ID to enqueue (Range 1 to 9223372036854775807)
   * The time at which the ID was added to the queue (24 Hr Format: DDMMYYYYHHMMSS )
   * The method returns the WorkOrder enqueued.
   * Usage: (POST) "/workorders?id=123456&amp;createdTs=30032016183015"
   *
   * @param id identifier for Work Order Request
   * @param createdTs Timestamp Work Order was created
   * @return WorkOrder
   */
  @RequestMapping(value = "/workorders", method = RequestMethod.POST)
  public WorkOrder enqueueWorkOrder(@RequestParam(value = "id") Long id,
                                    @RequestParam(value = "createdTs") String createdTs) {

    WorkOrder workOrder = new WorkOrder(id, createdTs);
    workOrder.isValid(workOrder);
    return workOrderQueueService.enqueueWorkOrder(workOrder);
  }

  /**
   * This endpoint removes the top ranked Work Order Request on the queue (dequeue).
   * The method returns the WorkOrder dequeued.
   * Usage: (DELETE) /workorders
   *
   * @return {@link WorkOrder}
   */
  @RequestMapping(value = "/workorders", method = RequestMethod.DELETE)
  public WorkOrder dequeueWorkOrder() {
    return workOrderQueueService.removeTopFromWorkOrderQueue();
  }

  /**
   * This endpoint retrieves the prioritised list of Work Order Request Id's.
   * The list is ranked highest prioity to lowest.
   * The method returns the List of Id's currently in the queue.
   * Usage: (GET) /workorders/ids
   *
   * @return {@link List} of Long Id's
   */
  @RequestMapping(value = "/workorders/ids", method = RequestMethod.GET)
  public List<Long> retrieveAllWorkOrderIds() {
    return workOrderQueueService.retrieveWorkOrderedIdList();
  }


  /**
   * This endpoint removes a specific Work Order Request on the queue (dequeue).
   * The Work Order is identified by the Id parameter.
   * The method returns the WorkOrder dequeued.
   * Usage: (DELETE) /workorders/ids?id=1234
   *
   * @param id identifier for Work Order Request
   * @return {@link WorkOrder}
   */
  @RequestMapping(value = "/workorders/ids", method = RequestMethod.DELETE)
  public WorkOrder dequeueWorkOrderId(@RequestParam(value = "id") Long id) {

    validationService.isIdValid(id);
    return workOrderQueueService.removeIdFromWorkOrderQueue(id);
  }

  /**
   * This endpoint retrieves the position on the queue fo a specific Work Order
   * Request on the queue.
   * The Work Order is identified by the Id parameter.
   * The method returns the position of the Work Order on the queue.
   * Usage: (GET) /workorders/ids/positions?id=1234
   *
   * @param id identifier for Work Order Request
   * @return int position in the queue
   */
  @RequestMapping(value = "/workorders/ids/positions", method = RequestMethod.GET)
  public int getWorkOrderQueuePosition(@RequestParam(value = "id") Long id) {
    validationService.isIdValid(id);
    return workOrderQueueService.retrieveIndexOfWorkOrderId(id);
  }


  /**
   * This endpoint retrieves the average wait time on the queue.
   * The method accepts a single parameter; the current time.
   * <p>
   * The method returns the average wait time on the queue.
   * Usage: (GET) /workorders/waittimes
   *
   * @param currentTs Timestamp Work Order was created (Format DDMMYYYYHHMMSS)
   * @return int position in the queue
   */
  @RequestMapping(value = "/workorders/waittimes", method = RequestMethod.GET)
  public Long retrieveMeanWaitTime(@RequestParam(value = "createdTs") String currentTs) {
    validationService.isCreatedTsValid(currentTs);
    return workOrderQueueService.retrieveAverageWaitTime(currentTs);

  }
}
