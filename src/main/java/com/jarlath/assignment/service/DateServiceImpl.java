package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@link DateServiceImpl} is the concrete implementation for the DateService
 * Interface. Provides date related services for use within the Work Order application.
 *
 * @author Jarlath Kelly
 * @see DateService
 */
@Service
public class DateServiceImpl implements DateService {

  public DateServiceImpl() {

  }

  /**
   * Converts a String of the format ddMMyyyyHHmmss into a
   * Date object
   *
   * @param dateIn String representation of a date
   * @return Date
   * @throws ParseException when supplied Timestamp has issues parsing
   */
  protected Date convertStringToDate(String dateIn) throws ParseException, InvalidTimestampParameterException {
    if (null == dateIn) {
      throw new InvalidTimestampParameterException();
    }
    SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
    return format.parse(dateIn);
  }

  /**
   * Calculates the length of time in seconds between a supplied date String
   * and the current time. Used to calculate the number of seconds a Work
   * Order has spent on the queue
   *
   * @param date String representation of a date
   * @return Long no of Seconds on the queue
   * @throws ParseException when supplied Timestamp has issues parsing
   */
  public Long getSecondsOnQueue(String date) throws ParseException, InvalidTimestampParameterException {
    if (null == date) {
      throw new InvalidTimestampParameterException();
    }
    Date incoming = convertStringToDate(date);
    Date current = new Date();
    return (current.getTime() - incoming.getTime()) / 1000;
  }

  /**
   * Calculates the length of time in seconds between 2 supplied date Strings
   * Used to calculate the number of seconds a Work Order has spent on the queue
   *
   * @param date String representation of a date
   * @return Long no of Seconds on the queue
   * @throws ParseException when supplied Timestamp has issues parsing
   */
  public Long getSecondsOnQueueUntilSpecifiedTime(String date, String currentTime) throws ParseException, InvalidTimestampParameterException {
    if (null == date || null == currentTime) {
      throw new InvalidTimestampParameterException();
    }
    Date incoming = convertStringToDate(date);
    Date current = convertStringToDate(currentTime);
    return (current.getTime() - incoming.getTime()) / 1000;

  }
}
