package com.jarlath.assignment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jarlath.kelly on 29/03/2016.
 * <p>
 * Utility Class to facilitate Unit testing
 */
public class TestUtilities {

  public TestUtilities() {
  }

  public String getADateString(int hour) {
    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
    Date currentDate = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(currentDate);
    cal.add(Calendar.HOUR, -hour);
    Date yesterday = cal.getTime();
    return formatter.format(yesterday);
  }
}
