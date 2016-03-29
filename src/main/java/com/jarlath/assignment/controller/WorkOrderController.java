package com.jarlath.assignment.controller;

import java.text.ParseException;
import java.util.List;
import com.jarlath.assignment.exception.TimeStampParsingException;
import com.jarlath.assignment.dto.WorkOrder;
import com.jarlath.assignment.service.ValidationService;
import com.jarlath.assignment.service.WorkOrderQueueService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkOrderController {

  ValidationService validationService = new ValidationService();
  WorkOrderQueueService workOrderQueueService = new WorkOrderQueueService();

  /**
   * An endpoint for adding a ID to queue (enqueue). This endpoint should
   * accept two parameters, the ID to enqueue and the time at which the ID
   * was added to the queue.
   *
   * @param id
   * @param createdTs
   * @return
   */

  @RequestMapping(value = "/workorders", method = RequestMethod.POST)
  public WorkOrder enqueueWorkOrder(@RequestParam(value = "id") Long id,
                                    @RequestParam(value = "createdTs") String createdTs) {

    WorkOrder workOrder = new WorkOrder(id, createdTs);
    workOrder.isValid(workOrder);
    return workOrderQueueService.enqueueWorkOrder(workOrder);
  }


  /**
   * An endpoint for getting the top ID from the queue and removing it (de-
   * queue). This endpoint should return the highest ranked ID and the time
   * it was entered into the queue.
   */
  @RequestMapping(value = "/workorders", method = RequestMethod.DELETE)
  public WorkOrder dequeueWorkOrder() {
    return workOrderQueueService.removeTopFromWorkOrderQueue();
  }


  /**
   * An endpoint for getting the list of IDs in the queue. This endpoint should
   * return a list of IDs sorted from highest ranked to lowest.
   *
   * @return
   */
  @RequestMapping(value = "/workorders/ids", method = RequestMethod.GET)
  public List<Long> retrieveAllWorkOrderIds() {
    return workOrderQueueService.retrieveWorkOrderedIdList();
  }


  /**
   * An endpoint for removing a specific ID from the queue. This endpoint
   * should accept a single parameter, the ID to remove.
   *
   * @param id
   * @return
   */
  @RequestMapping(value = "/workorders/ids", method = RequestMethod.DELETE)
  public WorkOrder dequeueWorkOrderId(@RequestParam(value = "id") Long id) {

    validationService.isIdValid(id);
    return workOrderQueueService.removeIdFromWorkOrderQueue(id);
  }


  /**
   * An endpoint to get the position of a specific ID in the queue. This endpoint
   * should accept one parameter, the ID to get the position of. It should return
   * the position of the ID in the queue indexed from 0.
   */
  @RequestMapping(value = "/workorders/ids/positions", method = RequestMethod.GET)
  public int getWorkOrderQueuePosition(@RequestParam(value = "id") Long id) {
    validationService.isIdValid(id);
    return workOrderQueueService.retrieveIndexOfWorkOrderId(id);
  }

  /**
   * An endpoint to get the average wait time. This endpoint should accept a
   * single parameter, the current time, and should return the average (mean)
   * number of seconds that each ID has been waiting in the queue.
   *
   * @return
   */

  @RequestMapping(value = "/workorders/waittimes", method = RequestMethod.GET)
  public Long retrieveMeanWaitTime(@RequestParam(value = "createdTs") String currentTs) {
      validationService.isCreatedTsValid(currentTs);
      return workOrderQueueService.retrieveAverageWaitTime(currentTs);

  }
}
