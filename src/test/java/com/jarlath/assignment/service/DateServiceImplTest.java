package com.jarlath.assignment.service;

import com.jarlath.assignment.TestUtilities;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import com.jarlath.assignment.util.Statics;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 29/03/2016.
 * <p>
 * Unit tests for the DateServiceImpl class
 */
public class DateServiceImplTest {

  DateServiceImpl dateService = new DateServiceImpl();
  public final static String BAD_DATE = "160419xxxx82183020";
  public final static Long ONE_HOUR = 3599L;
  TestUtilities testUtil = new TestUtilities();

  @Test
  public void test_convertStringToDate() throws ParseException {
    String testDate = "16041982183020";
    assertTrue(convertStringToDate(testDate) != null);
  }

  @Test(expected = ParseException.class)
  public void test_convertStringToDate_ParseException() throws ParseException {
    String testDate = BAD_DATE;
    convertStringToDate(testDate);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_convertStringToDate_InvalidTimestampParameterException() throws ParseException {
    convertStringToDate(null);
  }

  @Test
  public void test_getSecondsOnQueue() {
    String testDate = testUtil.getADateString(1);
    Long seconds = dateService.getSecondsOnQueue(testDate);
    assertTrue(seconds > ONE_HOUR);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getSecondsOnQueue_InvalidTimestampParameterException1()  {
    dateService.getSecondsOnQueue(BAD_DATE);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getSecondsOnQueue_InvalidTimestampParameterException2()  {
    dateService.getSecondsOnQueue(null);
  }

  @Test
  public void test_getSecondsOnQueueUntilSpecifiedTime() {
    Long seconds = dateService.getSecondsOnQueueUntilSpecifiedTime(testUtil.getADateString(2), testUtil.getADateString(1));
    assertTrue(seconds > ONE_HOUR);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getSecondsOnQueueUntilSpecifiedTime_InvalidTimestampParameterException1() {
    dateService.getSecondsOnQueueUntilSpecifiedTime(BAD_DATE, testUtil.getADateString(1));
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getSecondsOnQueueUntilSpecifiedTime_InvalidTimestampParameterException2()  {
    dateService.getSecondsOnQueueUntilSpecifiedTime(testUtil.getADateString(2), BAD_DATE);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getSecondsOnQueueUntilSpecifiedTime_InvalidTimestampParameterException3()  {
    dateService.getSecondsOnQueueUntilSpecifiedTime(null, BAD_DATE);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_getSecondsOnQueueUntilSpecifiedTime_InvalidTimestampParameterException4()  {
    dateService.getSecondsOnQueueUntilSpecifiedTime(testUtil.getADateString(2), null);
  }

  @Test
  public void test_jodaOperations()  {
    String dateTime = "28032016131455";
    DateTimeFormatter formatter = DateTimeFormat.forPattern(Statics.TIMESTAMP_FORMAT);
    DateTime incoming = formatter.parseDateTime(dateTime);
    assertTrue(incoming != null);
    DateTime now = DateTime.now();
    Seconds seconds = Seconds.secondsBetween(now,now);
    assertTrue(seconds.getSeconds() == 0);

  }

  /**
   * Converts a String of the format ddMMyyyyHHmmss into a
   * Date object
   *
   * @param dateIn String representation of a date
   * @return Date
   * @throws ParseException when supplied Timestamp has issues parsing
   */
  protected Date convertStringToDate(final String dateIn) throws ParseException, InvalidTimestampParameterException {
    if (null == dateIn) {
      throw new InvalidTimestampParameterException();
    }
    SimpleDateFormat format = new SimpleDateFormat(Statics.TIMESTAMP_FORMAT);
    return format.parse(dateIn);
  }






}
