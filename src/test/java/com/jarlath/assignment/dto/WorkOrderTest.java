package com.jarlath.assignment.dto;

import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 26/03/2016.
 */
public class WorkOrderTest {

  @Test
  public void test_Natural_And_Ranked_Ordering() {
    WorkOrder order1 = new WorkOrder(1l, getADateString(1));
    WorkOrder order2 = new WorkOrder(2l, getADateString(1));
    WorkOrder order3 = new WorkOrder(3l, getADateString(1));
    WorkOrder order4 = new WorkOrder(4l, getADateString(1));
    WorkOrder order5 = new WorkOrder(5l, getADateString(1));
    WorkOrder order6 = new WorkOrder(6l, getADateString(1));
    WorkOrder order7 = new WorkOrder(7l, getADateString(1));
    WorkOrder order8 = new WorkOrder(8l, getADateString(1));
    WorkOrder order9 = new WorkOrder(9l, getADateString(1));
    WorkOrder order10 = new WorkOrder(10l, getADateString(1));
    WorkOrder order12 = new WorkOrder(12l, getADateString(1));
    WorkOrder order15 = new WorkOrder(15l, getADateString(1));
    WorkOrder order20 = new WorkOrder(20l, getADateString(1));
    WorkOrder order30 = new WorkOrder(30l, getADateString(1));
    WorkOrder order40 = new WorkOrder(40l, getADateString(1));
    WorkOrder order45 = new WorkOrder(45l, getADateString(1));
    WorkOrder order50 = new WorkOrder(50l, getADateString(1));
    WorkOrder order60 = new WorkOrder(60l, getADateString(1));

    List<WorkOrder> l = new ArrayList<WorkOrder>();
    l.add(order1);
    l.add(order2);
    l.add(order3);
    l.add(order4);
    l.add(order5);
    l.add(order6);
    l.add(order7);
    l.add(order8);
    l.add(order9);
    l.add(order10);
    l.add(order12);
    l.add(order15);
    l.add(order20);
    l.add(order30);
    l.add(order40);
    l.add(order45);
    l.add(order50);
    l.add(order60);

    //Natural Order
    Collections.sort(l);
    assertTrue(l.get(0).getId() == 1);
    assertTrue(l.get(1).getId() == 2);
    assertTrue(l.get(2).getId() == 3);
    assertTrue(l.get(3).getId() == 4);
    assertTrue(l.get(4).getId() == 5);
    assertTrue(l.get(5).getId() == 6);
    assertTrue(l.get(6).getId() == 7);
    assertTrue(l.get(7).getId() == 8);
    assertTrue(l.get(8).getId() == 9);
    assertTrue(l.get(9).getId() == 10);
    assertTrue(l.get(10).getId() == 12);
    assertTrue(l.get(11).getId() == 15);
    assertTrue(l.get(12).getId() == 20);
    assertTrue(l.get(13).getId() == 30);
    assertTrue(l.get(14).getId() == 40);
    assertTrue(l.get(15).getId() == 45);
    assertTrue(l.get(16).getId() == 50);
    assertTrue(l.get(17).getId() == 60);

    Collections.sort(l, new WorkOrder());
    assertTrue(l.get(0).getId() == 15);
    assertTrue(l.get(1).getId() == 30);
    assertTrue(l.get(2).getId() == 45);
    assertTrue(l.get(3).getId() == 60);
    assertTrue(l.get(4).getId() == 5);
    assertTrue(l.get(5).getId() == 10);
    assertTrue(l.get(6).getId() == 20);
    assertTrue(l.get(7).getId() == 40);
    assertTrue(l.get(8).getId() == 50);
    assertTrue(l.get(9).getId() == 3);
    assertTrue(l.get(10).getId() == 6);
    assertTrue(l.get(11).getId() == 9);
    assertTrue(l.get(12).getId() == 12);
    assertTrue(l.get(13).getId() == 1);
    assertTrue(l.get(14).getId() == 2);
    assertTrue(l.get(15).getId() == 4);
    assertTrue(l.get(16).getId() == 7);
    assertTrue(l.get(17).getId() == 8);
  }


  private String getADateString(int hour) {
    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
    Date currentDate = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(currentDate);
    cal.add(Calendar.HOUR, -hour);
    Date yesterday = cal.getTime();
    return formatter.format(yesterday);

  }


}
