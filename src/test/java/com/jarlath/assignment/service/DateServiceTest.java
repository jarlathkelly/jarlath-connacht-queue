package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 27/03/2016.
 */
public class DateServiceTest {

  DateServiceImpl dateService = new DateServiceImpl();
  public final static String BAD_DATE = "160419xxxx82183020";
  public final static Long ONE_HOUR = 3599L;

  @Test
  public void test_convertStringToDate() throws ParseException {
    String testDate = "16041982183020";
    Date dateResult = dateService.convertStringToDate(testDate);
    assertTrue(dateResult.toString().equals("Fri Apr 16 18:30:20 IST 1982"));
  }

  @Test(expected = ParseException.class)
  public void test_convertStringToDate_ParseException() throws ParseException {
    String testDate = BAD_DATE;
    dateService.convertStringToDate(testDate);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_convertStringToDate_InvalidTimestampParameterException() throws ParseException {
    dateService.convertStringToDate(null);
  }

  @Test
  public void test_getSecondsOnQueue() throws ParseException {
    String testDate = getADateString(1);
    Long seconds = dateService.getSecondsOnQueue(testDate);
    assertTrue(seconds > ONE_HOUR);
  }

  @Test(expected = ParseException.class)
  public void test_getSecondsOnQueue_ParseException() throws ParseException {
    dateService.getSecondsOnQueue(BAD_DATE);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getSecondsOnQueue_InvalidTimestampParameterException() throws ParseException {
    dateService.getSecondsOnQueue(null);
  }

  @Test
  public void test_getSecondsOnQueueUntilSpecifiedTime() throws ParseException {
    Long seconds = dateService.getSecondsOnQueueUntilSpecifiedTime(getADateString(2), getADateString(1));
    assertTrue(seconds > ONE_HOUR);
  }

  @Test(expected = ParseException.class)
  public void test_getSecondsOnQueueUntilSpecifiedTime_ParseException1() throws ParseException {
    dateService.getSecondsOnQueueUntilSpecifiedTime(BAD_DATE, getADateString(1));
  }

  @Test(expected = ParseException.class)
  public void test_getSecondsOnQueueUntilSpecifiedTime_ParseException2() throws ParseException {
    dateService.getSecondsOnQueueUntilSpecifiedTime(getADateString(2), BAD_DATE);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getSecondsOnQueueUntilSpecifiedTime_InvalidTimestampParameterException1() throws ParseException {
    dateService.getSecondsOnQueueUntilSpecifiedTime(null, BAD_DATE);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getSecondsOnQueueUntilSpecifiedTime_InvalidTimestampParameterException2() throws ParseException {
    dateService.getSecondsOnQueueUntilSpecifiedTime(getADateString(2), null);
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
